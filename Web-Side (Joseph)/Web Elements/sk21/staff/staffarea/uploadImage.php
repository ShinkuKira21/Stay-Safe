<?php
    $targDirectory = "uploads/";
    $productImageName = basename($_FILES['img']['name']);

    $uploadPath = $targDirectory.$productImageName;

    $productImageFT = strtolower(pathinfo($uploadPath, PATHINFO_EXTENSION));

    $uploaded = true;

    if (file_exists($uploadPath)) 
    {
        $fileStatus = "Sorry, file already exists.";
        $uploadPath = "N/A"; // sets to N/A
        $uploaded = false;
    }

    if($uploaded && move_uploaded_file($_FILES['img']['tmp_name'], $uploadPath))
		$fileStatus = "The file has been uploaded";
?>

