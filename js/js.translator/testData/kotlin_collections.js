function ListIterator(list, index) {
    this.list = list;
    this.size = list.size;
    this.index = (index === undefined) ? 0 : index;
}
ListIterator.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    baseClasses: [Kotlin.kotlin.collections.ListIterator]
};
ListIterator.prototype.hasNext = function() {
    return this.index < this.size;
};
ListIterator.prototype.nextIndex = function() {
    return this.index;
};
ListIterator.prototype.next = function() {
    var index = this.index;
    var result = this.list.get_za3lpa$(index);
    this.index = index + 1;
    return result;
};
ListIterator.prototype.hasPrevious = function() {
    return this.index > 0;
};
ListIterator.prototype.previousIndex = function() {
    return this.index - 1;
};
ListIterator.prototype.previous = function() {
    var index = this.index - 1;
    var result = this.list.get_za3lpa$(index);
    this.index = index;
    return result;
};

function AbstractCollection() {
}
AbstractCollection.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    baseClasses: [Kotlin.kotlin.collections.MutableCollection]
};
AbstractCollection.prototype.addAll_wtfk93$ = function(collection) {
    var modified = false;
    var it = collection.iterator();
    while (it.hasNext()) {
        if (this.add_za3rmp$(it.next())) {
            modified = true;
        }
    }
    return modified
};
AbstractCollection.prototype.removeAll_wtfk93$ = function(c) {
    var modified = false;
    var it = this.iterator();
    while (it.hasNext()) {
        if (c.contains_za3rmp$(it.next())) {
            it.remove();
            modified = true;
        }
    }
    return modified
};
AbstractCollection.prototype.retainAll_wtfk93$ = function(c) {
    var modified = false;
    var it = this.iterator();
    while (it.hasNext()) {
        if (!c.contains_za3rmp$(it.next())) {
            it.remove();
            modified = true;
        }
    }
    return modified
};
AbstractCollection.prototype.clear = function() {
    throw new Kotlin.kotlin.NotImplementedError("Not implemented yet, see KT-7809");
};
AbstractCollection.prototype.containsAll_wtfk93$ = function(c) {
    var it = c.iterator();
    while (it.hasNext()) {
        if (!this.contains_za3rmp$(it.next())) return false;
    }
    return true;
};
AbstractCollection.prototype.isEmpty = function() {
    return this.size === 0;
};
AbstractCollection.prototype.iterator = function() {
    // TODO: Do not implement mutable iterator() this way, make abstract
    return Kotlin.arrayIterator(this.toArray());
};
AbstractCollection.prototype.equals_za3rmp$ = function(o) {
    if (this.size !== o.size) return false;

    var iterator1 = this.iterator();
    var iterator2 = o.iterator();
    var i = this.size;
    while (i-- > 0) {
        if (!Kotlin.equals(iterator1.next(), iterator2.next())) {
            return false;
        }
    }

    return true;
};
AbstractCollection.prototype.toString = function() {
    var builder = "[";
    var iterator = this.iterator();
    var first = true;
    var i = this.size;
    while (i-- > 0) {
        if (first) {
            first = false;
        }
        else {
            builder += ", ";
        }
        builder += Kotlin.toString(iterator.next());
    }
    builder += "]";
    return builder;
};
AbstractCollection.prototype.toJSON = function() {
    return this.toArray();
};
Kotlin.AbstractCollection = AbstractCollection;

function AbstractList() {
}
AbstractList.prototype = Object.create(AbstractCollection.prototype);
AbstractList.prototype.constructor = AbstractList;
AbstractList.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    baseClasses: [Kotlin.kotlin.collections.MutableList, AbstractCollection]
};
AbstractList.prototype.iterator = function() {
    return new ListIterator(this);
};
AbstractList.prototype.listIterator = function() {
    return new ListIterator(this);
};
AbstractList.prototype.listIterator_za3lpa$ = function(index) {
    if (index < 0 || index > this.size) {
        throw new Kotlin.java.lang.IndexOutOfBoundsException("Index: " + index + ", size: " + this.size);
    }
    return new ListIterator(this, index);
};
AbstractList.prototype.add_za3rmp$ = function(element) {
    this.add_vux3hl$(this.size, element);
    return true;
};
AbstractList.prototype.addAll_j97iir$ = function(index, collection) {
    // TODO: implement
    throw new Kotlin.kotlin.NotImplementedError("Not implemented yet, see KT-7809");
};
AbstractList.prototype.remove_za3rmp$ = function(o) {
    var index = this.indexOf_za3rmp$(o);
    if (index !== -1) {
        this.removeAt_za3lpa$(index);
        return true;
    }
    return false;
};
AbstractList.prototype.clear = function () {
    // TODO: implement with remove range
    throw new Kotlin.kotlin.NotImplementedError("Not implemented yet, see KT-7809");
};
AbstractList.prototype.contains_za3rmp$ = function(o) {
    return this.indexOf_za3rmp$(o) !== -1;
};
AbstractList.prototype.indexOf_za3rmp$ = function(o) {
    var i = this.listIterator();
    while (i.hasNext())
        if (Kotlin.equals(i.next(), o))
            return i.previousIndex();
    return -1;
};
AbstractList.prototype.lastIndexOf_za3rmp$ = function(o) {
    var i = this.listIterator_za3lpa$(this.size);
    while (i.hasPrevious())
        if (Kotlin.equals(i.previous(), o))
            return i.nextIndex();
    return -1;
};
AbstractList.prototype.subList_vux9f0$ = function(fromIndex, toIndex) {
    if (fromIndex < 0 || toIndex > this.size)
        throw new Kotlin.java.lang.IndexOutOfBoundsException();
    if (fromIndex > toIndex)
        throw new Kotlin.java.lang.IllegalArgumentException();
    return new SubList(this, fromIndex, toIndex);
};
AbstractList.prototype.hashCode = function() {
    var result = 1;
    var i = this.iterator();
    while (i.hasNext()) {
        var obj = i.next();
        result = (31*result + Kotlin.hashCode(obj)) | 0;
    }
    return result;
};
Kotlin.AbstractList = AbstractList;

function SubList(list, fromIndex, toIndex) {
    this.list = list;
    this.offset = fromIndex;
    this._size = toIndex - fromIndex;
}
SubList.prototype = Object.create(AbstractList.prototype);
SubList.prototype.constructor = SubList;
SubList.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    baseClasses: [AbstractList]
};
SubList.prototype.get_za3lpa$ = function(index) {
    this.checkRange(index);
    return this.list.get_za3lpa$(index + this.offset);
};
SubList.prototype.set_vux3hl$ = function(index, value) {
    this.checkRange(index);
    this.list.set_vux3hl$(index + this.offset, value);
};
Object.defineProperty(SubList.prototype, "size", {
    get: function () {
        return this._size;
    }
});
SubList.prototype.add_vux3hl$ = function(index, element) {
    if (index < 0 || index > this.size) {
        throw new Kotlin.java.lang.IndexOutOfBoundsException();
    }
    this.list.add_vux3hl$(index + this.offset, element);
};
SubList.prototype.removeAt_za3lpa$ = function(index) {
    this.checkRange(index);
    var result = this.list.removeAt_za3lpa$(index + this.offset);
    this._size--;
    return result;
};
SubList.prototype.checkRange = function(index) {
    if (index < 0 || index >= this._size) {
        throw new Kotlin.java.lang.IndexOutOfBoundsException();
    }
};

//TODO: should be JS Array-like (https://developer.mozilla.org/en-US/docs/JavaScript/Guide/Predefined_Core_Objects#Working_with_Array-like_objects)
function ArrayList() {
    this.array = [];
}
ArrayList.prototype = Object.create(AbstractList.prototype);
ArrayList.prototype.constructor = ArrayList;
ArrayList.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    baseClasses: [AbstractList, Kotlin.java.util.RandomAccess]
};
ArrayList.prototype.get_za3lpa$ = function(index) {
    this.checkRange(index);
    return this.array[index];
};
ArrayList.prototype.set_vux3hl$ = function(index, value) {
    this.checkRange(index);
    this.array[index] = value;
};
Object.defineProperty(ArrayList.prototype, "size", {
    get: function() {
        return this.array.length;
    }
});
ArrayList.prototype.iterator = function() {
    return Kotlin.arrayIterator(this.array);
};
ArrayList.prototype.add_za3rmp$ = function(element) {
    this.array.push(element);
    return true;
};
ArrayList.prototype.add_vux3hl$ = function(index, element) {
    this.array.splice(index, 0, element);
};
ArrayList.prototype.addAll_wtfk93$ = function(collection) {
    if (collection.size == 0) {
        return false;
    }
    var it = collection.iterator();
    for (var i = this.array.length, n = collection.size; n-- > 0;) {
        this.array[i++] = it.next();
    }
    return true;
};
ArrayList.prototype.removeAt_za3lpa$ = function(index) {
    this.checkRange(index);
    return this.array.splice(index, 1)[0];
};
ArrayList.prototype.clear = function() {
    this.array.length = 0;
};
ArrayList.prototype.indexOf_za3rmp$ = function(o) {
    for (var i = 0; i < this.array.length; i++) {
        if (Kotlin.equals(this.array[i], o)) {
            return i;
        }
    }
    return -1;
};
ArrayList.prototype.lastIndexOf_za3rmp$ = function(o) {
    for (var i = this.array.length - 1; i >= 0; i--) {
        if (Kotlin.equals(this.array[i], o)) {
            return i;
        }
    }
    return -1;
};
ArrayList.prototype.toArray = function() {
    return this.array.slice(0);
};
ArrayList.prototype.toString = function() {
    return Kotlin.arrayToString(this.array);
};
ArrayList.prototype.toJSON = function() {
    return this.array;
};
ArrayList.prototype.checkRange = function(index) {
    if (index < 0 || index >= this.array.length) {
        throw new Kotlin.java.lang.IndexOutOfBoundsException();
    }
};
Kotlin.ArrayList = ArrayList;
