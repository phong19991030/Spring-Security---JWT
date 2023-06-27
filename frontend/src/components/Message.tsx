import { Stomp } from "@stomp/stompjs";
import React, { useEffect, useRef, useState } from "react";
import { Cookies } from "react-cookie";
import SockJS from "sockjs-client";
import Menu from "./Menu";
import { MessageModel } from "../model/MessageModel";
import usePrevious from "./UsePrevious";

export default function Message() {
  const [message, setMessage] = useState<MessageModel>({});

  const [user, setUser] = useState<any>({});

  const [cmt, setCmt] = useState<any>({});

  const [listGroup, setListGroup] = useState<Array<any>>([]);

  const [listWarningId, setListWarningId] = useState<Array<any>>([]);

  const [listStompClient, setListStompClient] = useState<Array<any>>([]);

  const [currentStomp, setCurrentStomp] = useState<any>();

  const prevCmt = usePrevious(cmt);

  useEffect(() => {
    // Get user info in cookie
    const cookies = new Cookies();
    let userCookie = cookies.get("user");
    user && initParam(userCookie);
  }, []);

  const initParam = (userCookie: any) => {
    // init param when cookie is not null
    setUser(userCookie);
    setMessage({
      ...message,
      groupId: userCookie.listGroup[0].groupId,
      userName: userCookie.userName,
      insId: userCookie.userId,
    });
    setListGroup(userCookie.listGroup);
  };

  useEffect(() => {
    // open mutil connect
    opentMultiConnect();
  }, [user]);

  const opentMultiConnect = () => {
    listGroup &&
      listGroup.length > 0 &&
      listGroup.forEach((element: any, index: any) => {
        connect(element.groupId, index);
      });
  };

  function connect(groupId: any, index: any) {
    var socket = new SockJS("http://localhost:8081/msSocket");
    let stompClient = Stomp.over(socket);
    stompClient.connect({ userId: groupId }, function () {
      index === 0 && setCurrentStomp(stompClient);
      listStompClient.push(stompClient);
      setListStompClient({ ...listStompClient });
      stompClient.subscribe(
        "/users/queue/messages" + groupId,
        function (message: any) {
          let result: any = JSON.parse(message.body);
          cmt[groupId] = result.list;
          setCmt({ ...cmt });
        }
      );
    });
  }

  useEffect(() => {
    // Check Warring when have one or more message
    cmt &&
      prevCmt &&
      JSON.stringify(cmt) !== JSON.stringify(prevCmt) &&
      CheckWarning(cmt, prevCmt);
  }, [cmt]);

  const CheckWarning = (current: any, preState: any) => {
    for (const [key, value] of Object.entries(preState)) {
      current[key] &&
        current[key] !== value &&
        key !== message.groupId?.toString() &&
        setListWarningId([...listWarningId, key]);
    }
  };

  const remove = (data: any, stompClient: any) => {
    message &&
      stompClient.send("/app/msSocket/remove", {}, JSON.stringify(data));
  };

  function send(stompClient: any) {
    stompClient.send("/app/msSocket/send", {}, JSON.stringify(message));
    setMessage({ ...message, content: "" });
  }

  function init(stompClient: any) {
    stompClient.send("/app/msSocket/init", {}, JSON.stringify(message));
  }

  const changeGroup = (data: any) => {
    setMessage({
      ...message,
      groupId: data.groupId,
    });
    setCurrentStomp(listStompClient[data.index]);
    let listWarning = listWarningId.filter((element: any) => {
      return element.toString() != data.groupId;
    });
    setListWarningId(listWarning);
  };

  useEffect(() => {
    currentStomp && init(currentStomp);
  }, [currentStomp]);

  const handleChangeField = (event: any) => {
    let field = event.target.name;
    let value = event.target.value;
    setMessage({ ...message, [field]: value });
  };

  return (
    <>
      <Menu
        list={listGroup}
        currentGroupId={message.groupId}
        sendData={changeGroup}
        listWarningId={listWarningId}
      ></Menu>
      <div>{user.userName}</div>
      <input
        value={message.content}
        name="content"
        type="text"
        placeholder="content"
        onChange={handleChangeField}
      />
      <button
        onClick={() => {
          send(currentStomp);
        }}
      >
        send
      </button>
      <ul>
        {message.groupId &&
          cmt[message.groupId] &&
          cmt[message.groupId].map((element: MessageModel, index: number) => (
            <li key={index}>
              {element.userName} - {element.content}
              <button
                hidden={element.insId !== user.userId}
                onClick={() => {
                  remove(element, currentStomp);
                }}
              >
                remove
              </button>
            </li>
          ))}
      </ul>
    </>
  );
}
