<%-- 
    Document   : myslide
    Created on : Aug 5, 2014, 4:54:57 PM
    Author     : fupre1
--%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My slide</title>
        <link id='camera-css' href="resources/Style/camera.css"  type='text/css' media='all' >
            <style>
		html,body {
			height: 50%;
			margin: 0;
			padding: 0;
		}
		a {
			color: #09f;
		}
		a:hover {
			text-decoration: none;
		}
		#back_to_camera {
			background: rgba(255,255,255,.9);
			clear: both;
			display: block;
			height: 40px;
			line-height: 40px;
			padding: 20px;
			position: relative;
			z-index: 1;
		}
		.fluid_container {
			bottom: 0;
			height: 50%;
			left: 0;
			position: fixed;
			right: 0;
			top: 0;
			z-index: 0;
		}
		#camera_wrap_4 {
			bottom: 0;
			height: 50%;
			left: 0;
			margin-bottom: 0!important;
			position: fixed;
			right: 0;
			top: 0;
		}
		.camera_bar {
			z-index: 2;
		}
		.camera_thumbs {
			margin-top: -100px;
			position: relative;
			z-index: 1;
		}
		.camera_thumbs_cont {
			border-radius: 0;
			-moz-border-radius: 0;
			-webkit-border-radius: 0;
		}
		.camera_overlayer {
			opacity: .1;
		}
	</style>

<script src='resources/Script/jquery.min.js'></script>
<script src='resources/Script/jquery.mobile.customized.min.js'></script>
<script src="resources/Script/jquery.easing.1.3.js" ></script>
<script src="resources/Script/camera.min.js" ></script>
<script>
    jQuery(function(){

			jQuery('#camera_wrap_4').camera({
				height: 'auto',
				loader: 'bar',
				pagination: false,
				thumbnails: true,
				hover: false,
				opacityOnGrid: false,
				imagePath: '../images/'
			});

		});

</script>
    </head>
    <body>
       

        <div class="fluid_container">
        <div class="camera_wrap camera_emboss pattern_1" id="camera_wrap_4">
            <div data-thumb="resources/images/bansnos.png" data-src="resources/images/bansnos.png">
            </div>
            <div data-thumb="resources/slideimage/banner1.png" data-src="resources/slideimage/banner1.png">
            </div>
            <div data-thumb="resources/slideimage/snoc2.png" data-src="resources/slideimage/snoc2.png">
            </div>
            <div data-thumb="resources/slideimage/banner3.png" data-src="resources/slideimage/banner3.png">
            </div>
            <div data-thumb="resources/slideimage/banner4.png" data-src="resources/slideimage/banner4.png">
            </div>
            <div data-thumb="resources/slideimage/snosimage1.png" data-src="resources/slideimage/banner4.png">
            </div>
        </div><!-- #camera_wrap_3 -->

    </div><!-- .fluid_container -->

    </body>
</html>
