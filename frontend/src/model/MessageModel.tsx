export class MessageModel {
  userName?: string;
  content?: string;
  groupId?: string;
  insId?: string;

  constructor(userName: string, content: string) {
    this.userName = userName;
    this.content = content;
  }
}
