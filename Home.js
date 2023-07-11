
function checkForm()
{
    var name = document.getElementById("username1").value;
    var email = document.getElementById("email1").value;
    var password = document.getElementById("password2").value;
    var confirmPassword = document.getElementById("password4").value;
    if (name == '' || password == '' || email == '' || confirmPassword == '')
    {
        alert("Fill All Fields");
    }
    else
    {
        var name1 = document.getElementById("username");
        var email1 = document.getElementById("email");
        var password1 = document.getElementById("password1");
        var confirmPassword1 = document.getElementById("password3");
        if (name1.innerHTML == "username must be of minimum 8 characters" || email1.innerHTML == "not a valid email" ||confirmPassword1.innerHTML == "Password doesn\'t match"
        ||password1.innerHTML == "password must contain one uppercase character"|| password1.innerHTML == "password must contain one lowercase character"
        || password1.innerHTML == "password must contain one number"|| password1.innerHTML == "password must contain one special character"
        || password1.innerHTML == "password must be of minimum 8 characters"||name1.innerHTML=="Username already exists"||email1.innerHTML=="Email id already exists")
        {
            alert("Fill Valid Information");
        }
        else
        {
            insert(name,email,password);
            alert("Account Created");

        }
    }
}
function checkForm2()
{
    var otp=document.getElementById("hideotp").value;
    var email = document.getElementById("emailforgot").value;
    var password = document.getElementById("otppassword2").value;
    var confirmPassword = document.getElementById("otppassword4").value;
    if (password == '' || email == '' || confirmPassword == ''||otp=='')
    {
        alert("Fill All Fields");
    }
    else
    {
        var otpmsg=document.getElementById("hideotpmsg");
        var password1 = document.getElementById("otppassword1");
        var confirmPassword1 = document.getElementById("otppassword3");
        if ( confirmPassword1.innerHTML == "Password doesn\'t match"||otpmsg.innerHTML=="otp doesn't match"
        ||password1.innerHTML == "password must contain one uppercase character"|| password1.innerHTML == "password must contain one lowercase character"
        || password1.innerHTML == "password must contain one number"|| password1.innerHTML == "password must contain one special character"
        || password1.innerHTML == "password must be of minimum 8 characters")
        {
            alert("Fill Valid Information");
        }
        else
        {
            insert2(otp,email,password);
            var style2=document.getElementsByClassName('login');
            style2[0].style.display="block";
            var style3=document.getElementsByClassName('forgotClass');
            style3[0].style.display="none";


        }
    }
}
function insert2(otp,email,password)
{
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            alert(xmlhttp.responseText);
        }
        
    }
    xmlhttp.open("POST","http://localhost:8080/Ajax/Level3/change?email=" + email + "&password=" + password+ "&otp="+otp, true);
    xmlhttp.send();
}
function insert(name,email,password)
{
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            document.getElementById("account").innerHTML = xmlhttp.responseText;
        }
       
    }
    xmlhttp.open("POST","http://localhost:8080/Ajax/Level3/form?name=" + name + "&email=" + email+ "&password="+password, true);
    xmlhttp.send();
}

function sendotp(email)
{
    console.log(email);
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            alert(xmlhttp.responseText);
            document.getElementById("otpbutton").style.display="none";
            document.getElementById("hide1").style.display="none";
            document.getElementById("hide2").style.display="none";
            document.getElementById("emailforgot").style.marginTop="-10%";
            document.getElementById("hidingotp").style.display="block";
            
        }
        
    }
    xmlhttp.open("POST","http://localhost:8080/Ajax/Level3/mailing?email=" + email , true);
    xmlhttp.send();
}
async function validate(field,query)
{
    return new Promise((resolve)=>{
        xmlhttp = new XMLHttpRequest();

        xmlhttp.onreadystatechange = function()
        {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
            {
                resolve(xmlhttp.responseText);
            }

        }

        xmlhttp.open("POST","http://localhost:8080/Ajax/Level3/form?field=" + field + "&query=" + query, true);

        xmlhttp.send();
    });

}

async function validation(field,query){
    try
    {
        let validate1;
        if(field==="username")
        {
            validate1 = await usernameValidate(query);
        }
        else if(field==="email")
        {
            validate1 = await emailValidate(query);
        }
        console.log("hello"+validate1);
        let  validate2 = await validate(field,validate1);
        document.getElementById(field).innerHTML=validate2;

    }
    catch(err)
    {
        console.log(field);
        document.getElementById(field).innerHTML=err;

    }
}
async function usernameValidate(username)
{
    return new Promise((resolve,reject)=>{
        if(username.length<8)
        {
            reject("username must be of minimum 8 characters");
        }
        else
        {
            resolve(username);

        }
    });

}
async function emailValidate(email)
{
    return new Promise((resolve,reject)=>{
    var mailformat = /^([a-zA-Z0-9\.-]+)@([a-zA-Z0-9]+).([a-z]{2,5})(.[a-z]{2,5})?$/;
    if(email.match(mailformat))
    {
        resolve(email);
    }
    else
    {
        reject("not a valid email");
    }
    })

}
function passwordValidate(field,password)
{
    var regularExpression  = /^[a-zA-Z0-9!@#$%^&*]{8,16}$/;
    if(regularExpression.test(username))
    {
        document.getElementById(field).innerHTML="condition matches";
    }
    else
    {

        if(!password.match(/[A-Z]/g))
        {
            document.getElementById(field).innerHTML="password must contain one uppercase character";
        }
        else if(!password.match(/[a-z]/g))
        {
            document.getElementById(field).innerHTML="password must contain one lowercase character";
        }
        else if(!password.match(/[0-9]/g))
        {
            document.getElementById(field).innerHTML="password must contain one number ";
        }
        else if(password.length<8)
        {
            document.getElementById(field).innerHTML="password must be of minimum 8 characters ";
        }
        else if(!password.match(/[@$#]/g))
        {
            document.getElementById(field).innerHTML="password must contain one special character";
        }
        else
        {
            document.getElementById(field).innerHTML="";
        }

    }
}
function confirmValidate(field,query1,query2)
{
    if(query1===query2)
    {
        document.getElementById(field).innerHTML="";
    }
    else
    {

        document.getElementById(field).innerHTML="Password doesn't match";
    }
}
function signupstyle()
{
    console.log("its inside");
    var style1=document.getElementsByClassName('signUp');
    style1[0].style.display="block";
    var style2=document.getElementsByClassName('login');
    style2[0].style.display="none";
    var style3=document.getElementById("signUpBtn");
    style3.style.borderBottom="2px solid rgb(91, 243, 131)";
    var style4=document.getElementById("loginBtn");
    style4.style.borderBottom="0px solid rgb(91, 243, 131)";
    var style5=document.getElementsByClassName('forgotClass');
    style5[0].style.display="none";

}

function loginstyle()
{
    var style1=document.getElementsByClassName('signUp');
    style1[0].style.display="none";
    var style2=document.getElementsByClassName('login');
    style2[0].style.display="block";
    var style3=document.getElementById("loginBtn");
    style3.style.borderBottom="2px solid rgb(91, 243, 131)";
    var style4=document.getElementById("signUpBtn");
    style4.style.borderBottom="0px solid rgb(91, 243, 131)";
    var style5=document.getElementsByClassName('forgotClass');
    style5[0].style.display="none";
}

function enableForgotPassword()
{
    
    var style1=document.getElementsByClassName('forgotClass');
    style1[0].style.display="block";
    style1[0].style.marginLeft="40px";
    document.getElementById("otpbutton").style.display="block";
    document.getElementById("emailforgot").style.marginTop="10%";
    document.getElementById("hide1").style.display="block";
    document.getElementById("hide2").style.display="block";
    var style2=document.getElementsByClassName('login');
    style2[0].style.display="none";
    style2[0].reset();
    var style3=document.getElementById('hidingotp');
    style3.style.display="none";
    style3.reset();

}

function signinform()
{
    var email = document.getElementById("emaillogin").value;
    var password = document.getElementById("passwordlogin").value;
    console.log(email+" "+password);
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            alert(xmlhttp.responseText);
        }
        
    }
    xmlhttp.open("POST","http://localhost:8080/Ajax/Level3/login?email=" + email + "&password=" + password);
    xmlhttp.send();
}
