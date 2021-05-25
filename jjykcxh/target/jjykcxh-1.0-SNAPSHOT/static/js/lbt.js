var index=0;
var mun=0;
setTimeout(function(){
  var dots = document.getElementsByClassName("dot");
  var slides = document.getElementsByClassName("mySlides");
  slides[0].style.display='block';
  dots[0].className += " active";
},0);
function ChangeImg(){
  index++;
  var dots = document.getElementsByClassName("dot");
  var slides = document.getElementsByClassName("mySlides");
  if(index>=slides.length) index=0;
  if (index< 0) {index = slides.length-1;}
  for(var i=0;i<slides.length;i++)
  {
    slides[i].style.display='none';
  }
  changedot();
  slides[index].style.display='block';
}
function changedot(){
  var dots = document.getElementsByClassName("dot");
  if(index>=dots.length) index=0;
  for (var i = 0; i < dots.length; i++) {
  dots[i].className = dots[i].className.replace(" active", "");
}
  dots[index].className += " active";
}
 setInterval(ChangeImg,3000);

 function current(n){
  clear();
  var dots = document.getElementsByClassName("dot");
  var slides = document.getElementsByClassName("mySlides");
  slides[n-1].style.display='block';
  dots[n-1].className += " active";
}

function clear(){
  var slides = document.getElementsByClassName("mySlides");
  if(index>=slides.length) index=0;
  for(var i=0;i<slides.length;i++)
  {
    slides[i].style.display='none';
  }
  var dots = document.getElementsByClassName("dot");
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
}

function plusSlides(n) {
  if(n===1){
  ChangeImg();
}
else if(n===-1){
  index-=2;
  ChangeImg();
}
}

function currentSlide(n) {
  current(n);
}