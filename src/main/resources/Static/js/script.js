function openNav() {
    document.getElementById("employeenav").style.width = "200px";
    document.getElementById("wrapper").style.marginLeft = "-200px";
    document.getElementById("info").style.width = "90%";
    document.getElementById("closebtn").style.display = "block";
    document.getElementById("openbtn").style.display = "none";
}

function closeNav() {
    document.getElementById("employeenav").style.width = "0";
    document.getElementById("wrapper").style.marginLeft= "0";
    document.getElementById("info").style.width = "100%";
    document.getElementById("closebtn").style.display = "none";
    document.getElementById("openbtn").style.display = "block";
}