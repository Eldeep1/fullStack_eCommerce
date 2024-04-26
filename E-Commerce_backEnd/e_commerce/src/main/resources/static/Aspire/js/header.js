
function logout(){
    localStorage.clear();
    window.location.href = "login.html"
}


function profileDropDown(){
    if(localStorage.getItem("logged") === "true"){
        $("#loginout").empty();

        $("#loginout").append(`
            <li class="dropdown cart-nav dropdown-slide">
                <a href="#!" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"><b>${localStorage.getItem("userName")}<b></a>
                <div class="dropdown-menu cart-dropdown">
                    <a href="profile-details.html" class="btn btn-main btn-small btn-round">Profile</a>
                    <a href="#" class="btn btn-main btn-small btn-round" id="logout">Logout</a>
                </div>
            </li>
        `)

        $("#logout").click(logout);
    }
}

$(document).ready(function(){
    // profileDropDown runs after header-bar.html is loaded into element with class top-header
    $(".top-header").load("header-bar.html", profileDropDown);
    $("#main-menu").load("main-menu.html");
});
