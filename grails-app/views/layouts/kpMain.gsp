<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="KennelPal"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<g:layoutHead/>
		<r:layoutResources />
	</head>
	<body>
		<div id="kpLogoBlockMini" class="divCenter" role="banner">
			<img src="${resource(dir: 'images', file: 'kpLogoBlockMiniGray.png')}" alt="kpLogoBlockMini"/>
		</div>
		<div id = "kpAdminBar" class="divCenter">
			<img src="${resource(dir: 'images', file: 'kpAdminBar.png')}" alt="kpAdminBar"/></a></div>
			<g:link action="list" controller="user">Users     </g:link>
			<g:link action="list" controller="rate">Rates</g:link>
			<br/>
		</div>
		<g:layoutBody/>
		<div class="footer" role="contentinfo"></div>
					<img src="${resource(dir: 'images', file: 'kpFooterGray.png')}" alt="kpFooter"/></a></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<g:javascript library="application"/>
		<r:layoutResources />
	</body>
</html>