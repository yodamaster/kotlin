function A(y) {
    this.y = y;
}
A.prototype.f = function(x) {
    return "A.f(" + (x + this.y) + ")";
};
A.prototype.v = function() {
    return arguments.length + this.y;
};

var O = {
    f: function(x) {
        return "O.f(" + x + ")";
    },
    P : {
        f: function(x) {
            return "O.P.f(" + x + ")";
        }
    }
};