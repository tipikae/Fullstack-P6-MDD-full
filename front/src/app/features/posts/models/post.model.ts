export interface Post {
    id: number;
    title: string;
    content: string;
    authorId: number;
    authorUsername: string;
    topicId: number;
    topicName: string;
    createdAt: Date;
}