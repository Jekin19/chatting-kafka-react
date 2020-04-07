import React, { Component } from "react";
import { Launcher } from "react-chat-window";
import Stomp from "stompjs";
import SockJS from "sockjs-client";

export default class ChatMessenger extends Component {
  constructor() {
    super();
    this.state = {
      messageList: [],
      isOpen: true,
      newMessagesCount: 0,
    };
  }
  id = Date.now().toString();
  socket = new SockJS("http://localhost:8080/chatting");
  stompClient = Stomp.over(this.socket);

  componentDidMount() {
    this.stompClient.connect({}, (frame) => {
      this.stompClient.subscribe("/topic/chattingMessage", (notification) => {
        this.addMessage(JSON.parse(notification.body));
      });
      this.stompClient.subscribe("/topic/fileMessage", (notification) => {
        this.addMessage(JSON.parse(notification.body));
      });
    });
  }

  addMessage = (message) => {
    message = { ...message, author: message.author === this.id ? "me" : "them" };
    this.setState((state) => ({
      messageList: state.messageList.concat(message),
      newMessagesCount: state.isOpen ? state.newMessagesCount : state.newMessagesCount + 1,
    }));
  };

  _onMessageWasSent(message) {
    this.stompClient.send("/app/chatMessage", {}, JSON.stringify({ ...message, author: this.id }));
  }

  _onFilesSelected(fileList) {
    const objectURL = window.URL.createObjectURL(fileList[0]);
    const message = {
      type: "file",
      author: this.id,
      data: {
        url: objectURL,
        fileName: fileList[0].name,
      },
    };
    this.stompClient.send("/app/fileMessage", {}, JSON.stringify(message));
  }

  _handleClick() {
    this.setState((state) => ({
      isOpen: !state.isOpen,
      newMessagesCount: 0,
    }));
  }

  render() {
    return (
      <div>
        <Launcher
          isOpen={this.state.isOpen}
          agentProfile={{
            teamName: "Chat Window",
            imageUrl: "https://a.slack-edge.com/66f9/img/avatars-teams/ava_0001-34.png",
          }}
          onMessageWasSent={this._onMessageWasSent.bind(this)}
          messageList={this.state.messageList}
          showEmoji
          handleClick={this._handleClick.bind(this)}
          onFilesSelected={this._onFilesSelected.bind(this)}
          newMessagesCount={this.state.newMessagesCount}
        />
      </div>
    );
  }
}
