export interface Post {
    id?: number;
    title: string;
    content: string;
    userId?: number;
    userName?: string;
    topicId: number;
    topicName?: string;
    createdAt?: Date;
}