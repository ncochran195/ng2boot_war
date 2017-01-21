export class Greeting {
    private _id: number;
    private _content: string;

    constructor(id: number, content: string) {
        this._id = id;
        this._content = content;
    }

    get id(): number {
        return this._id;
    }
    set id(id: number) {
        this._id = id;
    }

    get content(): string {
        return this._content;
    }
    set content(content: string) {
        this._content = content;
    }
}