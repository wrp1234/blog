interface Result {
    code: number;
    message: string;
    data?: any;
    timestamp: number;
}

interface Menu {
    id: number,
    createTime: Date,
    parentId: number,
    name: string,
    type: number,
    url: string,
    sort: number,
}

interface UserInfo {
    id: number,
    username: string,
    photoId: number,
    token: string
}

export type { Result, UserInfo, Menu }