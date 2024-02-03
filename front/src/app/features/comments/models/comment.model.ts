export interface Comment {
    id?: number;
    comment: string;
    authorId?: number;
    authorUsername?: string;
    postId?: number;
    createdAt?: Date;
}