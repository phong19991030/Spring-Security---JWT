import React, { useEffect, useState } from "react";
import { Cookies } from "react-cookie";
import { UserService } from "../service/UserService";

export default function Profile() {
  const cookies = new Cookies();

  useEffect(() => {
    getUserInfo();
  });

  const [data, setData] = useState<any>();

  const getUserInfo = () => {
    let token = cookies.get("token");
    let type = cookies.get("type");
    debugger;
    token &&
      UserService.getInstance()
        .getUserInfo(token, type)
        .then((response) => {
          setData(response.data);
          debugger;
        });
  };

  return (
    <>
      <div>Profile</div>
      <div>{data}</div>
    </>
  );
}
