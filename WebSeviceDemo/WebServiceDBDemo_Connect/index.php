<?php
include("controller/c_demo.php");
$c_Demo = new c_demo();
$type_expenseName = $_POST['type_expenseName'];
$content = $c_Demo->selectTypeExpense($type_expenseName);
echo json_encode($content['typeExpense']);
print_r($content);
?>