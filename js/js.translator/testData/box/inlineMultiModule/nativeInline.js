function A(y) {
    this.y = y;
}
A.prototype.f = function(x) {
    return "A.f(" + (x + this.y) + ")";
};
