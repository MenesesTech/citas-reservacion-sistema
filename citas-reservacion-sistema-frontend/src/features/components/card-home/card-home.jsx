import "./card-home.css";

export const CardHome = ({
  messagetitle,
  messagesubtitle,
  imagesrc,
  cardColor,
}) => {
  return (
    <div className="card-3x mt-5" style={{ backgroundColor: cardColor }}>
      <div class="row gx-4">
        <div className="col d-flex justify-content-center align-items-center">
          <img src={imagesrc} alt="" />
        </div>
        <div className="col justify-content-center align-items-center">
          <h3 className="temp">{messagetitle}</h3>
          <h3 className="m">{messagesubtitle}</h3>
        </div>
      </div>
    </div>
  );
};
