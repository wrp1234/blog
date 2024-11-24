interface Result {
    code: number;
    message: string;
    data?: any;
    timestamp: number;
}

interface UserInfo {
    id: number,
    username: string,
    photoId: number,
    token: string
}

export type { Result, UserInfo }