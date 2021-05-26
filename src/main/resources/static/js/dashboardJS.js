let intro = document.querySelector(".splash-intro");
let logo = document.querySelector(".splash-logo-header");
let logoSpan = document.querySelectorAll(".splash-logo");

window.addEventListener('DOMContentLoaded', ()=>{

    setTimeout(() =>{

        logoSpan.forEach((span, idx)=>
        {
            setTimeout(()=>
            {
                span.classList.add('active');
            }, (idx + 1) * 400)
        });

        setTimeout(()=>
        {
            logoSpan.forEach((span, idx)=>
            {

                setTimeout(()=>
                {
                    span.classList.remove('active');
                    span.classList.add('fade');
                }, (idx +1) * 50)
            });
        },2000);

        setTimeout(()=>
        {
            intro.style.top = '-100vh';
        }, 2300)
    })
})