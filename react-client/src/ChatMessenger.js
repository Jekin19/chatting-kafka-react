import React, { Component } from "react";
import { Launcher } from "react-chat-window";
import Stomp from "stompjs";
import SockJS from "sockjs-client";

export default class ChatMessenger extends Component {
  constructor() {
    super();
    this.state = {
      messageList: [],
    };
  }
  id = Date.now().toString();
  socket = new SockJS("http://localhost:8080/chatting");
  stompClient = Stomp.over(this.socket);

  componentDidMount() {
    this.stompClient.connect({}, (frame) => {
      this.stompClient.subscribe("/topic/chatting_Message", (notification) => {
        this.addMessage(JSON.parse(notification.body));
      });
    });
  }

  addMessage = (message) => {
    message = { ...message, author: message.author === this.id ? "me" : "them" };
    this.setState({
      messageList: this.state.messageList.concat(message),
    });
  };

  _onMessageWasSent(message) {
    this.stompClient.send("/app/chatMessage", {}, JSON.stringify({ ...message, author: this.id }));
  }

  render() {
    return (
      <div>
        <Launcher
          isOpen
          agentProfile={{
            teamName: "Chat Window",
            imageUrl: "https://a.slack-edge.com/66f9/img/avatars-teams/ava_0001-34.png",
          }}
          onMessageWasSent={this._onMessageWasSent.bind(this)}
          messageList={this.state.messageList}
          showEmoji={false}
        />
      </div>
    );
  }
}
