export interface Comment {
    id?: number;
    comment: string;
    userId?: number;
    userName?: string;
    postId?: number;
    createdAt?: Date;
}