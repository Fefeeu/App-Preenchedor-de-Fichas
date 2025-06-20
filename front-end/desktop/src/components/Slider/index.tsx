import "./styles.css";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import slide1 from "../../assets/slides/1.png";
import slide2 from "../../assets/slides/2.png";
import slide3 from "../../assets/slides/3.png";
import slide4 from "../../assets/slides/4.png";

export function LandingSlider(){
    var settings = {
    dots: true,
    infinite: true,
    speed: 800,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 4000,
    pauseOnHover: true,
    customPaging: i => (
      <div
        style={{
          color: "#ff3e3e"
        }}
      >
        {"."}
      </div>
    )
};
  return (
    <div className="slider-container">
        <Slider {...settings}>
            <div>
                <img src={slide1} width="100%"/>
            </div>
            <div>
                <img src={slide2} width="100%"/>
            </div>
            <div>
                <img src={slide3} width="100%"/>
            </div>
            <div>
                <img src={slide4} width="100%"/>
            </div>
        </Slider>
    </div>
  );
}