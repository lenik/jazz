<?
	require '_Phpfixes.php'; 
	_RequireOnce('../mail.php'); 

	$mail = new phpx_mail; 
	$mail->Subject = 't-mail test program'; 
	$mail->Body = 'This is a mail test program. '; 
	$mail->AltBody = 'This is a mail test program. (Alt text)'; 
	$mail->AddAddress('dansei@163.com', 'test receiver'); 
	if ($mail->Send()) {
		echo "Send ok!"; 
	} else {
		echo "Send fail: $mail->ErrorInfo"; 
	}
?>
