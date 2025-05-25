function ReadOnlyField({ label, value }: { label: string; value: string }) {
  return (
    <>
      <div className="mb-3">
        <label className="form-label">{label}</label>
        <input type="text" className="form-control" value={value} disabled />
      </div>
    </>
  );
}
export default ReadOnlyField;
