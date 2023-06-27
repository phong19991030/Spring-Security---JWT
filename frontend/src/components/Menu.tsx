import React, { useEffect, useState } from "react";

export default function Menu(props: any) {
  const [list, setList] = useState<any[]>([]);

  const [listWarningId, setListWarningId] = useState<any[]>([]);

  const [currentGroupId, setCurrentGroupId] = useState<any>();

  useEffect(() => {
    props.list && setList(props.list);
    props.currentGroupId && setCurrentGroupId(props.currentGroupId);
    props.listWarningId && setListWarningId(props.listWarningId);
  });

  const handleChangeGroup = (data: any, index: any) => {
    setCurrentGroupId(data.groupId);
    props.sendData({
      groupId: data.groupId,
      index: index,
    });
  };

  return (
    <>
      <div>
        {list.map((element: any, index: any) => (
          <h1
            onClick={() => {
              handleChangeGroup(element, index);
            }}
            className={
              currentGroupId === element.groupId
                ? "d-flex text-primary"
                : "d-flex"
            }
          >
            {element.groupName}
            {listWarningId.includes(element.groupId.toString()) &&
              element.groupId !== currentGroupId && <p>*</p>}
          </h1>
        ))}
      </div>
    </>
  );
}
