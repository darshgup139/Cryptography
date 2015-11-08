<?php

session_start();
$string = '';

$string = chr(rand(65,90)) . chr(rand(97,122)) . chr(rand(65,90)) . chr(rand(65,90)) . chr(rand(97,122));

$_SESSION['rand_code'] = $string;

//Storage Spaces.
$dir = 'fonts/';
$image = imagecreatetruecolor(210, 100);//creates an image of specified width and height
$black = imagecolorallocate($image, 0, 0, 0);//black color defination
$color = imagecolorallocate($image, rand(50,205), rand(50,205), rand(50,205)); // generate random colors
$white = imagecolorallocate($image, 255, 255, 255);//white color defination

imagefilledrectangle($image,0,0,400,300,$white);//generates the canvas on which the image will be generated
imagettftext($image, rand(20,40), rand(0,35), 40, 80, $color, $dir."monofont.ttf", $_SESSION['rand_code']);//places the font onto the canvas to form the image.

header("Content-type: image/png");
imagepng($image);
?>