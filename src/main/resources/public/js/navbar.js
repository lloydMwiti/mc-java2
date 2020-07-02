let drawer=document.querySelector('.hidden-nav');
let burger=document.querySelector('.burger');

burger.addEventListener('click',()=>{
    drawer.classList.toggle('drawer');
})