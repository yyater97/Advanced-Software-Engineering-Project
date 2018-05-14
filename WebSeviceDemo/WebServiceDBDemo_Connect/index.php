<?php
include("controller/c_demo.php");
$c_Demo = new c_demo();
$content = $c_Demo->selectTypeExpense();
echo json_encode($content['typeExpense']);
print_r($content);
?>