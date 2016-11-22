/*
 * Copyright 2010-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


// TODO: Make Entry implement kotlin.Map.Entry interface
function Entry(key, value) {
    this.key = key;
    this.value = value;
}
Entry.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    baseClasses: []
};
Entry.prototype.hashCode = function() {
    return mapEntryHashCode(this.key, this.value);
};
Entry.prototype.equals_za3rmp$ = function(o) {
    // TODO: Check if o is instance of kotlin.Map.Entry
    return o instanceof Entry && Kotlin.equals(this.key, o.key) && Kotlin.equals(this.value, o.value);
};
Entry.prototype.toString = function() {
    return Kotlin.toString(this.key) + "=" + Kotlin.toString(this.value);
};

function hashMapPutAll(fromMap) {
    var entries = fromMap.entries;
    var it = entries.iterator();
    while (it.hasNext()) {
        var e = it.next();
        this.put_wn2jw4$(e.key, e.value);
    }
}

function hashSetEquals(o) {
    if (o == null || this.size !== o.size) return false;

    return this.containsAll_wtfk93$(o);
}

function hashSetHashCode() {
    var h = 0;
    var i = this.iterator();
    while (i.hasNext()) {
        var obj = i.next();
        h += Kotlin.hashCode(obj);
    }
    return h;
}

function convertKeyToString(key) { return key; }
function convertKeyToNumber(key) { return +key; }
function convertKeyToBoolean(key) { return key == "true"; }

var FUNCTION = "function";
var arrayRemoveAt = (typeof Array.prototype.splice == FUNCTION) ?
                    function (arr, idx) {
                        arr.splice(idx, 1);
                    } :

                    function (arr, idx) {
                        var itemsAfterDeleted, i, len;
                        if (idx === arr.length - 1) {
                            arr.length = idx;
                        }
                        else {
                            itemsAfterDeleted = arr.slice(idx + 1);
                            arr.length = idx;
                            for (i = 0, len = itemsAfterDeleted.length; i < len; ++i) {
                                arr[idx + i] = itemsAfterDeleted[i];
                            }
                        }
                    };

function hashObject(obj) {
    if (obj == null) return "";

    var hashCode;
    if (typeof obj == "string") {
        return obj;
    }
    else if (typeof obj.hashCode == FUNCTION) {
        // Check the hashCode method really has returned a string
        hashCode = obj.hashCode();
        return (typeof hashCode == "string") ? hashCode : hashObject(hashCode);
    }
    else if (typeof obj.toString == FUNCTION) {
        return obj.toString();
    }
    else {
        try {
            return String(obj);
        }
        catch (ex) {
            // For host objects (such as ActiveObjects in IE) that have no toString() method and throw an error when
            // passed to String()
            return Object.prototype.toString.call(obj);
        }
    }
}

function mapEntryHashCode(key, value) {
    return Kotlin.hashCode(key) ^ Kotlin.hashCode(value);
}

function equals_fixedValueHasEquals(fixedValue, variableValue) {
    return fixedValue.equals_za3rmp$(variableValue);
}

function equals_fixedValueNoEquals(fixedValue, variableValue) {
    return (variableValue != null && typeof variableValue.equals_za3rmp$ == FUNCTION) ?
           // TODO: test this case
           variableValue.equals_za3rmp$(fixedValue) : (fixedValue === variableValue);
}

function Bucket(hash, firstKey, firstValue, equalityFunction) {
    this[0] = hash;
    this.entries = [];
    this.addEntry(firstKey, firstValue);

    if (equalityFunction !== null) {
        this.getEqualityFunction = function () {
            return equalityFunction;
        };
    }
}

var EXISTENCE = 0, ENTRY = 1, ENTRY_INDEX_AND_VALUE = 2;

function createBucketSearcher(mode) {
    return function (key) {
        var i = this.entries.length, entry, equals = this.getEqualityFunction(key);
        while (i--) {
            entry = this.entries[i];
            if (equals(key, entry[0])) {
                switch (mode) {
                    case EXISTENCE:
                        return true;
                    case ENTRY:
                        return entry;
                    case ENTRY_INDEX_AND_VALUE:
                        return [ i, entry[1] ];
                }
            }
        }
        return false;
    };
}

function createBucketLister(entryProperty) {
    return function (aggregatedArr) {
        var startIndex = aggregatedArr.length;
        for (var i = 0, len = this.entries.length; i < len; ++i) {
            aggregatedArr[startIndex + i] = this.entries[i][entryProperty];
        }
    };
}

Bucket.prototype = {
    getEqualityFunction: function (searchValue) {
        return (searchValue != null && typeof searchValue.equals_za3rmp$ == FUNCTION) ? equals_fixedValueHasEquals : equals_fixedValueNoEquals;
    },

    getEntryForKey: createBucketSearcher(ENTRY),

    getEntryAndIndexForKey: createBucketSearcher(ENTRY_INDEX_AND_VALUE),

    removeEntryForKey: function (key) {
        var result = this.getEntryAndIndexForKey(key);
        if (result) {
            arrayRemoveAt(this.entries, result[0]);
            return result;
        }
        return null;
    },

    addEntry: function (key, value) {
        this.entries[this.entries.length] = [key, value];
    },

    keys: createBucketLister(0),

    values: createBucketLister(1),

    getEntries: function (entries) {
        var startIndex = entries.length;
        for (var i = 0, len = this.entries.length; i < len; ++i) {
            // Clone the entry stored in the bucket before adding to array
            entries[startIndex + i] = this.entries[i].slice(0);
        }
    },

    containsKey_za3rmp$: createBucketSearcher(EXISTENCE),

    containsValue_za3rmp$: function (value) {
        var i = this.entries.length;
        while (i--) {
            if (value === this.entries[i][1]) {
                return true;
            }
        }
        return false;
    }
};

/*----------------------------------------------------------------------------------------------------------------*/

// Supporting functions for searching hashtable buckets

function searchBuckets(buckets, hash) {
    var i = buckets.length, bucket;
    while (i--) {
        bucket = buckets[i];
        if (hash === bucket[0]) {
            return i;
        }
    }
    return null;
}

function getBucketForHash(bucketsByHash, hash) {
    var bucket = bucketsByHash[hash];

    // Check that this is a genuine bucket and not something inherited from the bucketsByHash's prototype
    return ( bucket && (bucket instanceof Bucket) ) ? bucket : null;
}

/*----------------------------------------------------------------------------------------------------------------*/

function Hashtable(hashingFunctionParam, equalityFunctionParam) {
    this.buckets = [];
    this.bucketsByHash = {};

    this.hashingFunction = (typeof hashingFunctionParam == FUNCTION) ? hashingFunctionParam : hashObject;
    this.equalityFunction = (typeof equalityFunctionParam == FUNCTION) ? equalityFunctionParam : null;
    this._size = 0;
}
Hashtable.prototype = Object.create(Kotlin.AbstractCollection.prototype);
Hashtable.prototype.constructor = Hashtable;
Hashtable.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    baseClasses: [Kotlin.AbstractCollection]
};

Hashtable.prototype.put_wn2jw4$ = function (key, value) {
    var hash = this.hashingFunction(key), bucket, bucketEntry, oldValue = null;

    // Check if a bucket exists for the bucket key
    bucket = getBucketForHash(this.bucketsByHash, hash);
    if (bucket) {
        // Check this bucket to see if it already contains this key
        bucketEntry = bucket.getEntryForKey(key);
        if (bucketEntry) {
            // This bucket entry is the current mapping of key to value, so replace old value and we're done.
            oldValue = bucketEntry[1];
            bucketEntry[1] = value;
        }
        else {
            // The bucket does not contain an entry for this key, so add one
            bucket.addEntry(key, value);
            this._size++;
        }
    }
    else {
        // No bucket exists for the key, so create one and put our key/value mapping in
        bucket = new Bucket(hash, key, value, this.equalityFunction);
        this.buckets[this.buckets.length] = bucket;
        this.bucketsByHash[hash] = bucket;
        this._size++;
    }
    return oldValue;
};

Hashtable.prototype.get_za3rmp$ = function (key) {
    var hash = this.hashingFunction(key);

    // Check if a bucket exists for the bucket key
    var bucket = getBucketForHash(this.bucketsByHash, hash);
    if (bucket) {
        // Check this bucket to see if it contains this key
        var bucketEntry = bucket.getEntryForKey(key);
        if (bucketEntry) {
            // This bucket entry is the current mapping of key to value, so return the value.
            return bucketEntry[1];
        }
    }
    return null;
};

Hashtable.prototype.containsKey_za3rmp$ = function (key) {
    var bucketKey = this.hashingFunction(key);

    // Check if a bucket exists for the bucket key
    var bucket = getBucketForHash(this.bucketsByHash, bucketKey);

    return bucket ? bucket.containsKey_za3rmp$(key) : false;
};

Hashtable.prototype.containsValue_za3rmp$ = function (value) {
    var i = this.buckets.length;
    while (i--) {
        if (this.buckets[i].containsValue_za3rmp$(value)) {
            return true;
        }
    }
    return false;
};

Hashtable.prototype.clear = function () {
    this.buckets.length = 0;
    this.bucketsByHash = {};
    this._size = 0;
};

Hashtable.prototype.isEmpty = function () {
    return !this.buckets.length;
};

Hashtable.prototype._keys = function () {
    var aggregated = [], i = this.buckets.length;
    while (i--) {
        this.buckets[i].keys(aggregated);
    }
    return aggregated;
};

Hashtable.prototype._values = function () {
    var aggregated = [], i = this.buckets.length;
    while (i--) {
        this.buckets[i].values(aggregated);
    }
    return aggregated;
};

Hashtable.prototype._entries = function () {
    var aggregated = [], i = this.buckets.length;
    while (i--) {
        this.buckets[i].getEntries(aggregated);
    }
    return aggregated;
};

Object.defineProperty(Hashtable.prototype, "values", {
    get: function () {
        var values = this._values();
        var i = values.length;
        var result = new Kotlin.ArrayList();
        while (i--) {
            result.add_za3rmp$(values[i]);
        }
        return result;
    },
    configurable: true
});

Hashtable.prototype.remove_za3rmp$ = function (key) {
    var hash = this.hashingFunction(key), bucketIndex, oldValue = null, result = null;

    // Check if a bucket exists for the bucket key
    var bucket = getBucketForHash(this.bucketsByHash, hash);

    if (bucket) {
        // Remove entry from this bucket for this key
        result = bucket.removeEntryForKey(key);
        if (result !== null) {
            this._size--;
            oldValue = result[1];

            // Entry was removed, so check if bucket is empty
            if (!bucket.entries.length) {
                // Bucket is empty, so remove it from the bucket collections
                bucketIndex = searchBuckets(this.buckets, hash);
                arrayRemoveAt(this.buckets, bucketIndex);
                delete this.bucketsByHash[hash];
            }
        }
    }
    return oldValue;
};

Object.defineProperty(Hashtable.prototype, "size", {
    get: function () {
        return this._size;
    }
});

Hashtable.prototype.each = function (callback) {
    var entries = this._entries(), i = entries.length, entry;
    while (i--) {
        entry = entries[i];
        callback(entry[0], entry[1]);
    }
};

Hashtable.prototype.putAll_r12sna$ = hashMapPutAll;

Hashtable.prototype.clone = function () {
    var clone = new Hashtable(this.hashingFunction, this.equalityFunction);
    clone.putAll_r12sna$(this);
    return clone;
};

Object.defineProperty(Hashtable.prototype, "keys", {
    get: function () {
         var res = new HashSet();
         var keys = this._keys();
         var i = keys.length;
         while (i--) {
             res.add_za3rmp$(keys[i]);
         }
         return res;
    },
    configurable: true
});

Object.defineProperty(Hashtable.prototype, "entries", {
    get: function () {
         var result = new HashSet();
         var entries = this._entries();
         var i = entries.length;
         while (i--) {
             var entry = entries[i];
             result.add_za3rmp$(new Entry(entry[0], entry[1]));
         }

         return result;
    },
    configurable: true
});

Hashtable.prototype.hashCode = function() {
    var h = 0;
    var entries = this._entries();
    var i = entries.length;
    while (i--) {
        var entry = entries[i];
        h += mapEntryHashCode(entry[0], entry[1]);
    }
    return h;
};

Hashtable.prototype.equals_za3rmp$ = function(o) {
    if (o == null || this.size !== o.size) return false;

    var entries = this._entries();
    var i = entries.length;
    while (i--) {
        var entry = entries[i];
        var key = entry[0];
        var value = entry[1];
        if (value == null) {
            if (!(o.get_za3rmp$(key) == null && o.contains_za3rmp$(key))) return false;
        }
        else {
            if (!Kotlin.equals(value, o.get_za3rmp$(key))) return false;
        }
    }
    return true;
};

Hashtable.prototype.toString = function() {
    var entries = this._entries();
    var length = entries.length;
    if (length === 0) return "{}";
    var builder = "{";
    for (var i = 0;;) {
        var entry = entries[i];
        var key = entry[0];
        var value = entry[1];
        builder +=
            (key === this ? "(this Map)" : Kotlin.toString(key)) +
            "=" +
            (value === this ? "(this Map)" : Kotlin.toString(value));
        if (++i >= length) return builder + "}";
        builder += ", "
    }
};

function HashMap() {
    Hashtable.call(this);
}
HashMap.prototype = Object.create(Hashtable.prototype);
HashMap.prototype.constructor = HashMap;
HashMap.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    baseClasses: [Hashtable, Kotlin.kotlin.collections.MutableMap]
};
Kotlin.ComplexHashMap = HashMap;
Kotlin.HashMap = HashMap;

function PrimitiveHashMapValuesIterator(map, keys) {
    this.map = map;
    this.keys = keys;
    this.size = keys.length;
    this.index = 0;
}
PrimitiveHashMapValuesIterator.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    baseClasses: [Hashtable, Kotlin.kotlin.collections.Iterator]
};
PrimitiveHashMapValuesIterator.prototype.next = function() {
    if (!this.hasNext()) {
        throw new Kotlin.java.lang.NoSuchElementException();
    }
    return this.map[this.keys[this.index++]];
};
PrimitiveHashMapValuesIterator.prototype.hasNext = function() {
    return this.index < this.size;
};

function PrimitiveHashMapValues(map) {
    this.map = map;
}
PrimitiveHashMapValues.prototype = Object.create(Kotlin.AbstractCollection);
PrimitiveHashMapValues.prototype.constructor = PrimitiveHashMapValues;
PrimitiveHashMapValues.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    baseClasses: [Kotlin.AbstractCollection]
};
PrimitiveHashMapValues.prototype.iterator = function() {
    return new PrimitiveHashMapValuesIterator(this.map.map, Object.keys(this.map.map));
};
PrimitiveHashMapValues.prototype.isEmpty = function() {
    return this.map.isEmpty();
};
Object.defineProperty(PrimitiveHashMapValues.prototype, "size", {
    get: function () {
        return this.map.size;
    }
});
PrimitiveHashMapValues.prototype.contains_za3rmp$ = function(o) {
    return this.map.containsValue_za3rmp$(o);
};

function AbstractPrimitiveHashMap() {
    HashMap.call(this);
    this.$size = 0;
    this.map = Object.create(null);
}
AbstractPrimitiveHashMap.prototype = Object.create(HashMap.prototype);
AbstractPrimitiveHashMap.prototype.constructor = AbstractPrimitiveHashMap;
AbstractPrimitiveHashMap.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    baseClasses: [HashMap]
};
Object.defineProperty(AbstractPrimitiveHashMap.prototype, "size", {
    get: function () {
        return this.$size;
    }
});
AbstractPrimitiveHashMap.prototype.isEmpty = function() {
    return this.$size === 0;
};
AbstractPrimitiveHashMap.prototype.containsKey_za3rmp$ = function(key) {
    // TODO: should process "__proto__" separately?
    return this.map[key] !== void 0;
};
AbstractPrimitiveHashMap.prototype.containsValue_za3rmp$ = function(value) {
    var map = this.map;
    for (var key in map) {
        //noinspection JSUnfilteredForInLoop
        if (map[key] === value) {
            return true;
        }
    }

    return false;
};
AbstractPrimitiveHashMap.prototype.get_za3rmp$ = function(key) {
    return this.map[key];
};
AbstractPrimitiveHashMap.prototype.put_wn2jw4$ = function(key, value) {
    var prevValue = this.map[key];
    this.map[key] = value === void 0 ? null : value;
    if (prevValue === void 0) {
        this.$size++;
    }
    return prevValue;
};
AbstractPrimitiveHashMap.prototype.remove_za3rmp$ = function(key) {
    var prevValue = this.map[key];
    if (prevValue !== void 0) {
        delete this.map[key];
        this.$size--;
    }
    return prevValue;
};
AbstractPrimitiveHashMap.prototype.clear = function() {
    this.$size = 0;
    this.map = {};
};
AbstractPrimitiveHashMap.prototype.putAll_r12sna$ = hashMapPutAll;
Object.defineProperty(AbstractPrimitiveHashMap.prototype, "entries", {
    get: function() {
        var result = new HashSet();
        var map = this.map;
        for (var key in map) {
            //noinspection JSUnfilteredForInLoop
            result.add_za3rmp$(new Entry(this.convertKeyToKeyType(key), map[key]));
        }

        return result;
    }
});
AbstractPrimitiveHashMap.prototype.getKeySetClass = function() {
    throw new Error("Kotlin.AbstractPrimitiveHashMap.getKetSetClass is abstract");
};
AbstractPrimitiveHashMap.prototype.convertKeyToKeyType = function(key) {
    throw new Error("Kotlin.AbstractPrimitiveHashMap.convertKeyToKeyType is abstract");
};
Object.defineProperty(AbstractPrimitiveHashMap.prototype, "keys", {
    get: function() {
        var result = new (this.getKeySetClass())();
        var map = this.map;
        for (var key in map) {
            //noinspection JSUnfilteredForInLoop
            result.add_za3rmp$(key);
        }

        return result;
    }
});
Object.defineProperty(AbstractPrimitiveHashMap.prototype, "values", {
    get: function () {
        return new PrimitiveHashMapValues(this);
    }
});
AbstractPrimitiveHashMap.prototype.toJSON = function() {
    return this.map;
};
AbstractPrimitiveHashMap.prototype.toString = function() {
    if (this.isEmpty()) return "{}";
    var map = this.map;
    var isFirst = true;
    var builder = "{";
    for (var key in map) {
        var value = map[key];
        builder +=
            (isFirst ? "": ", ") +
            Kotlin.toString(key) +
            "=" +
            (value === this ? "(this Map)" : Kotlin.toString(value));
        isFirst = false;
    }
    return builder + "}";
};
AbstractPrimitiveHashMap.prototype.equals_za3rmp$ = function(o) {
    if (o == null || this.size !== o.size) return false;
    var map = this.map;
    for (var key in map) {
        var key_ = this.convertKeyToKeyType(key);
        var value = map[key];
        if (value == null) {
            if (!(o.get_za3rmp$(key_) == null && o.contains_za3rmp$(key_))) return false;
        }
        else {
            if (!Kotlin.equals(value, o.get_za3rmp$(key_))) return false;
        }
    }
    return true;
};
AbstractPrimitiveHashMap.prototype.hashCode = function() {
    var h = 0;
    var map = this.map;
    for (var key in map) {
        //noinspection JSUnfilteredForInLoop
        h += mapEntryHashCode(this.convertKeyToKeyType(key), map[key]);
    }
    return h;
};

function DefaultPrimitiveHashMap() {
    AbstractPrimitiveHashMap.call(this);
}
DefaultPrimitiveHashMap.prototype = Object.create(AbstractPrimitiveHashMap.prototype);
DefaultPrimitiveHashMap.prototype.constructor = DefaultPrimitiveHashMap;
DefaultPrimitiveHashMap.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    baseClasses: [AbstractPrimitiveHashMap]
};
DefaultPrimitiveHashMap.prototype.getKeySetClass = function() {
    return DefaultPrimitiveHashSet;
};
DefaultPrimitiveHashMap.prototype.convertKeyToKeyType = convertKeyToString;
Kotlin.DefaultPrimitiveHashMap = DefaultPrimitiveHashMap;

function PrimitiveNumberHashMap() {
    AbstractPrimitiveHashMap.call(this);
    this.$keySetClass$ = PrimitiveNumberHashSet;
}
PrimitiveNumberHashMap.prototype = Object.create(AbstractPrimitiveHashMap.prototype);
PrimitiveNumberHashMap.prototype.constructor = PrimitiveNumberHashMap;
PrimitiveNumberHashMap.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    baseClasses: [AbstractPrimitiveHashMap]
};
PrimitiveNumberHashMap.prototype.getKeySetClass = function() {
    return PrimitiveNumberHashSet;
};
PrimitiveNumberHashMap.prototype.convertKeyToKeyType = convertKeyToNumber;
Kotlin.PrimitiveNumberHashMap = PrimitiveNumberHashMap;

function PrimitiveBooleanHashMap() {
    AbstractPrimitiveHashMap.call(this);
}
PrimitiveBooleanHashMap.prototype = Object.create(AbstractPrimitiveHashMap.prototype);
PrimitiveBooleanHashMap.prototype.constructor = PrimitiveBooleanHashMap;
PrimitiveBooleanHashMap.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    baseClasses: [AbstractPrimitiveHashMap]
};
PrimitiveBooleanHashMap.prototype.getKeySetClass = function() {
    return PrimitiveBooleanHashSet;
};
PrimitiveBooleanHashMap.prototype.convertKeyToKeyType = convertKeyToBoolean;
Kotlin.PrimitiveBooleanHashMap = PrimitiveBooleanHashMap;

function LinkedHashMap() {
    HashMap.call(this);
    this.orderedKeys = [];
}
LinkedHashMap.prototype = Object.create(HashMap.prototype);
LinkedHashMap.prototype.constructor = LinkedHashMap;
LinkedHashMap.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    baseClasses: [HashMap]
};

LinkedHashMap.prototype.put_wn2jw4$ = function(key, value) {
    if (!this.containsKey_za3rmp$(key)) {
        this.orderedKeys.push(key);
    }

    return HashMap.prototype.put_wn2jw4$.call(this, key, value);
};
LinkedHashMap.prototype.remove_za3rmp$ = function(key) {
    var i = this.orderedKeys.indexOf(key);
    if (i != -1) {
        this.orderedKeys.splice(i, 1);
    }

    return HashMap.prototype.remove_za3rmp$.call(this, key);
};
LinkedHashMap.prototype.clear = function () {
    HashMap.prototype.clear.call(this);
    this.orderedKeys = [];
};
Object.defineProperty(LinkedHashMap.prototype, "keys", {
    get: function() {
        // TODO return special Set which unsupported adding
        var set = new Kotlin.LinkedHashSet();
        set.map = this;
        return set;
    }
});
Object.defineProperty(LinkedHashMap.prototype, "entries", {
    get: function() {
        var set = new Kotlin.LinkedHashSet();

        for (var i = 0, c = this.orderedKeys, l = c.length; i < l; i++) {
            set.add_za3rmp$(new Entry(c[i], this.get_za3rmp$(c[i])));
        }

        return set;
    }
});
Kotlin.LinkedHashMap = LinkedHashMap;

function HashSet(hashingFunction, equalityFunction) {
    this.hashTable = new Hashtable(hashingFunction, equalityFunction);
    this.hashingFunction = hashingFunction;
    this.equalityFunction = equalityFunction;
}
HashSet.prototype = Object.create(Kotlin.AbstractCollection.prototype);
HashSet.prototype.constructor = HashSet;
HashSet.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    baseClasses: [Kotlin.AbstractCollection, Kotlin.kotlin.collections.MutableSet]
};
HashSet.prototype.addAll_wtfk93$ = Kotlin.AbstractCollection.prototype.addAll_wtfk93$;
HashSet.prototype.removeAll_wtfk93$ = Kotlin.AbstractCollection.prototype.removeAll_wtfk93$;
HashSet.prototype.retainAll_wtfk93$ = Kotlin.AbstractCollection.prototype.retainAll_wtfk93$;
HashSet.prototype.containsAll_wtfk93$ = Kotlin.AbstractCollection.prototype.containsAll_wtfk93$;
HashSet.prototype.add_za3rmp$ = function (o) {
    return !this.hashTable.put_wn2jw4$(o, true);
};
HashSet.prototype.toArray = function () {
    return this.hashTable._keys();
};
HashSet.prototype.iterator = function () {
    return new SetIterator(this);
};
HashSet.prototype.remove_za3rmp$ = function (o) {
    return this.hashTable.remove_za3rmp$(o) != null;
};
HashSet.prototype.contains_za3rmp$ = function (o) {
    return this.hashTable.containsKey_za3rmp$(o);
};
HashSet.prototype.clear = function () {
    this.hashTable.clear();
};
Object.defineProperty(HashSet.prototype, "size", { get: function () {
    return this.hashTable.size;
}});
HashSet.prototype.isEmpty = function () {
    return this.hashTable.isEmpty();
};
HashSet.prototype.clone = function () {
    var h = new HashSet(this.hashingFunction, this.equalityFunction);
    h.addAll_wtfk93$(this.hashTable.keys());
    return h;
};
HashSet.prototype.equals_za3rmp$ = hashSetEquals;
HashSet.prototype.toString = function () {
    var builder = "[";
    var iter = this.iterator();
    var first = true;
    while (iter.hasNext()) {
        if (first) {
            first = false;
        }
        else {
            builder += ", ";
        }
        builder += iter.next();
    }
    builder += "]";
    return builder;
};
HashSet.prototype.intersection = function (hashSet) {
    var intersection = new HashSet(this.hashingFunction, this.equalityFunction);
    var values = hashSet.values, i = values.length, val;
    while (i--) {
        val = values[i];
        if (this.hashTable.containsKey_za3rmp$(val)) {
            intersection.add_za3rmp$(val);
        }
    }
    return intersection;
};
HashSet.prototype.union = function (hashSet) {
    var union = this.clone();
    var values = hashSet.values, i = values.length, val;
    while (i--) {
        val = values[i];
        if (!this.hashTable.containsKey_za3rmp$(val)) {
            union.add_za3rmp$(val);
        }
    }
    return union;
};
HashSet.prototype.isSubsetOf = function (hashSet) {
    var values = this.hashTable.keys(), i = values.length;
    while (i--) {
        if (!hashSet.contains_za3rmp$(values[i])) {
            return false;
        }
    }
    return true;
};
HashSet.prototype.hashCode = hashSetHashCode;
Kotlin.ComplexHashSet = HashSet;
Kotlin.HashSet = HashSet;

function LinkedHashSet() {
    HashSet.call(this);
    this.map = new LinkedHashMap();
}
LinkedHashSet.prototype = Object.create(HashSet.prototype);
LinkedHashSet.prototype.constructor = LinkedHashSet;
LinkedHashSet.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    baseClasses: [HashSet, Kotlin.kotlin.collections.MutableSet]
};
LinkedHashSet.prototype.equals_za3rmp$ = hashSetEquals;
LinkedHashSet.prototype.hashCode = hashSetHashCode;
Object.defineProperty(LinkedHashSet.prototype, "size", {
    get: function() {
        return this.map.size;
    }
});
LinkedHashSet.prototype.contains_za3rmp$ = function(element) {
    return this.map.containsKey_za3rmp$(element);
};
LinkedHashSet.prototype.iterator = function() {
    return new SetIterator(this);
};
LinkedHashSet.prototype.add_za3rmp$ = function(element) {
    return this.map.put_wn2jw4$(element, true) == null;
};
LinkedHashSet.prototype.remove_za3rmp$ = function(element) {
    return this.map.remove_za3rmp$(element) != null;
};
LinkedHashSet.prototype.clear = function() {
    this.map.clear();
};
LinkedHashSet.prototype.toArray = function() {
    return this.map.orderedKeys.slice();
};
Kotlin.LinkedHashSet = LinkedHashSet;

function SetIterator(set) {
    this.set = set;
    this.keys = set.toArray();
    this.index = 0;
}
SetIterator.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    baseClasses: [Kotlin.kotlin.collections.MutableIterator]
};
SetIterator.prototype.next = function() {
    if (!this.hasNext()) {
        throw new Kotlin.java.util.NoSuchElementException();
    }
    return this.keys[this.index++];
};
SetIterator.prototype.hasNext = function() {
    return this.index < this.keys.length;
};
SetIterator.prototype.remove = function () {
    if (this.index === 0) {
        throw Kotlin.java.lang.IllegalStateException();
    }
    this.set.remove_za3rmp$(this.keys[this.index - 1]);
};

function AbstractPrimitiveHashSet() {
    HashSet.call(this);
    this.$size = 0;
    this.map = Object.create(null);
}
AbstractPrimitiveHashSet.prototype = Object.create(HashSet.prototype);
AbstractPrimitiveHashSet.prototype.constructor = AbstractPrimitiveHashSet;
AbstractPrimitiveHashSet.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    baseClasses: [HashSet]
};
AbstractPrimitiveHashSet.prototype.equals_za3rmp$ = hashSetEquals;
AbstractPrimitiveHashSet.prototype.hashCode = hashSetHashCode;
Object.defineProperty(AbstractPrimitiveHashSet.prototype, "size", {
    get: function () {
        return this.$size;
    }
});
AbstractPrimitiveHashSet.prototype.contains_za3rmp$ = function(key) {
    return this.map[key] === true;
};
AbstractPrimitiveHashSet.prototype.iterator = function() {
    return new SetIterator(this);
};
AbstractPrimitiveHashSet.prototype.add_za3rmp$ = function(element) {
    var prevElement = this.map[element];
    this.map[element] = true;
    if (prevElement === true) {
        return false;
    }
    else {
        this.$size++;
        return true;
    }
};
AbstractPrimitiveHashSet.prototype.remove_za3rmp$ = function(element) {
    if (this.map[element] === true) {
        delete this.map[element];
        this.$size--;
        return true;
    }
    else {
        return false;
    }
};
AbstractPrimitiveHashSet.prototype.clear = function() {
    this.$size = 0;
    this.map = {};
};
AbstractPrimitiveHashSet.prototype.convertKeyToKeyType = function(key) {
    throw new Error("Kotlin.AbstractPrimitiveHashSet.convertKeyToKeyType is abstract");
};
AbstractPrimitiveHashSet.prototype.toArray = function () {
    var result = Object.keys(this.map);
    for (var i = 0; i < result.length; i++) {
        result[i] = this.convertKeyToKeyType(result[i]);
    }
    return result;
};

function DefaultPrimitiveHashSet() {
    AbstractPrimitiveHashSet.call(this);
}
DefaultPrimitiveHashSet.prototype = Object.create(AbstractPrimitiveHashSet.prototype);
DefaultPrimitiveHashSet.prototype.constructor = DefaultPrimitiveHashSet;
DefaultPrimitiveHashSet.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    baseClasses: [AbstractPrimitiveHashSet]
};
DefaultPrimitiveHashSet.prototype.toArray = function() {
    return Object.keys(this.map);
};
DefaultPrimitiveHashSet.prototype.convertKeyToKeyType = convertKeyToString;
Kotlin.DefaultPrimitiveHashSet = DefaultPrimitiveHashSet;

function PrimitiveNumberHashSet() {
    AbstractPrimitiveHashSet.call(this);
}
PrimitiveNumberHashSet.prototype = Object.create(AbstractPrimitiveHashSet.prototype);
PrimitiveNumberHashSet.prototype.constructor = PrimitiveNumberHashSet;
PrimitiveNumberHashSet.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    baseClasses: [AbstractPrimitiveHashSet]
};
PrimitiveNumberHashSet.prototype.convertKeyToKeyType = convertKeyToNumber;
Kotlin.PrimitiveNumberHashSet = PrimitiveNumberHashSet;

function PrimitiveBooleanHashSet() {
    AbstractPrimitiveHashSet.call(this);
}
PrimitiveBooleanHashSet.prototype = Object.create(AbstractPrimitiveHashSet.prototype);
PrimitiveBooleanHashSet.prototype.constructor = PrimitiveBooleanHashSet;
PrimitiveBooleanHashSet.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    baseClasses: [AbstractPrimitiveHashSet]
};
PrimitiveBooleanHashSet.prototype.convertKeyToKeyType = convertKeyToBoolean;
Kotlin.PrimitiveBooleanHashSet = PrimitiveBooleanHashSet;