export class Type4 {
    constructor(v3) {
        this.field = v3.field;
    }
    getField() {
        return this.field;
    }
}
let v1 = {
    field: 100,
};
let v2 = v1;
let v3 = v2;
let v4 = new Type4(v3);
console.log(v4);
