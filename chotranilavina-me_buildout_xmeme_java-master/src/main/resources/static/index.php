<!DOCTYPE html>
<html>
  
<head>
    <title>Insert Page page</title>
</head>
  
<body>
    <center>
        <?php
  
  $servername = "localhost";
  $username = "admin";
  $password = "Mahima10";
  $dbname = "authentication";
    
  // Create connection
  $conn = new mysqli($servername, 
      $username, $password, $dbname);
    
  // Check connection
  if ($conn->connect_error) {
      die("Connection failed: " 
          . $conn->connect_error);
  }
          
        // Taking all 5 values from the form data(input)
        $username =  $_REQUEST['username'];
        $password =  $_REQUEST['password'];
          
        // Performing insert query execution
        // here our table name is college
        $sql1 = "INSERT INTO users (username,password) VALUES ('$username', 
            '$password')";
        $sql2 = "INSERT INTO authorities (username,authority) VALUES ('$username','ROLE_USER')";
          
        if(mysqli_query($conn, $sql1) && mysqli_query($conn,$sql2)){
            echo "<h3>data stored in a database successfully." 
                . " Please browse your localhost php my admin" 
                . " to view the updated data</h3>"; 
        } else{
            echo "ERROR: Hush! Sorry $sql1. " 
                . mysqli_error($conn);
        }
          
        // Close connection
        mysqli_close($conn);
        ?>
    </center>
</body>
  
</html>