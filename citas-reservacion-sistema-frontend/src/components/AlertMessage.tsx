interface Props {
  type: "success" | "danger";
  message: string;
}

function AlertMessage({ type, message }: Props) {
  return (
    <>
      <div className={`alert alert-${type}`}>{message}</div>
    </>
  );
}

export default AlertMessage;
