import { Topic } from "../features/topics/models/topic.model";

export interface User {
    id: number;
    username: string;
    email: string;
    topics: Topic[];
    createdAt: Date;
    updatedAt: Date;
}