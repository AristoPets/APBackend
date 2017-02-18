// This is called with the results from from FB.getLoginStatus().

var accsrTokenInfo;



function post(path, params, value, method) {
	method = method || "post";
	// Set method to post by default if not specified.

	// The rest of this code assumes you are not using a library.
	// It can be made less wordy if you use one.
	var form = document.createElement("form");
	form.setAttribute("method", method);
	form.setAttribute("action", path);


			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", params);
			hiddenField.setAttribute("value", value);

			form.appendChild(hiddenField);


	document.body.appendChild(form);
	form.submit();
}










function statusChangeCallback(response) {



	if (response.status === 'connected') {
		// Logged into your app and Facebook.
		//send(response.authResponse.accessToken)

	} else if (response.status === 'not_authorized') {
		// The person is logged into Facebook, but not your app.
		// document.getElementById('status').innerHTML = 'Please log ' +
		//   'into this app.';
	} else {
		// The person is not logged into Facebook, so we're not sure if
		// they are logged into this app or not.
		// document.getElementById('status').innerHTML = 'Please log ' +
		//   'into Facebook.';
	}
}

// This function is called when someone finishes with the Login
// Button.  See the onlogin handler attached to it in the sample
// code below.
function checkLoginState() {
	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});
}

window.fbAsyncInit = function() {
	FB.init({
		appId      : '372236016446842',
		cookie     : true,  // enable cookies to allow the server to access
							// the session
		xfbml      : true,  // parse social plugins on this page
		version    : 'v2.8' // use graph api version 2.5
	});



	// Now that we've initialized the JavaScript SDK, we call
	// FB.getLoginStatus().  This function gets the state of the
	// person visiting this page and can return one of three states to
	// the callback you provide.  They can be:
	//
	// 1. Logged into your app ('connected')
	// 2. Logged into Facebook, but not your app ('not_authorized')
	// 3. Not logged into Facebook and can't tell if they are logged into
	//    your app or not.
	//
	// These three cases are handled in the callback function.

	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});

	FB.Event.subscribe('auth.login', function(response) {
		if (response.authResponse) {
			var test = JSON.stringify(response);
			post('/user', 'param', test, 'post');
			//post_to_url("/info", test);
			console.log(JSON.stringify(response));
		} else {
			post('/', 'param', "exit", 'get');
			console.log('User cancelled login or did not fully authorize.');
		}
	});

};

// Load the SDK asynchronously
(function(d, s, id) {
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id)) return;
	js = d.createElement(s); js.id = id;
	js.src = "//connect.facebook.net/ru_RU/sdk.js#xfbml=1&version=v2.8";
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

// Here we run a very simple test of the Graph API after login is
// successful.  See statusChangeCallback() for when this call is made.

// function testAPI(response) {

//   document.getElementById('token').innerHTML =
//     'Token: , ' + response.authResponse.accessToken;
//   document.getElementById('userID').innerHTML =
//     'UserID: , ' + response.authResponse.userID;
//   FB.api('/me?fields=id,name,email', function(response) {
//     console.log('Successful login for: ' + response.name);
//     document.getElementById('name').innerHTML =
//       'Name: , ' + response.name + '!';
//     document.getElementById('email').innerHTML =
//       'Email: , ' + response.email + '!';
//   });
// }

