export interface Type1 {
    field: number;
}

export interface Type2 {
    field?: number;
}

export interface Type3 {
    field?: number;
}

export class Type4 {
    constructor(v3: Type3) {
        this.field = v3.field;
    }

    private field: number;

    getField(): number {
        return this.field;
    }
}

let v1: Type1 = {
    field: 100,
}

let v2: Type2 = v1;

let v3: Type3 = v2;

let v4: Type4 = new Type4(v3);

console.log(v4);
