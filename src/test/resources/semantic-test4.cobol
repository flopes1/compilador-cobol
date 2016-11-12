IDENTIFICATORDIVISION. PROGRAMID. testeRetorno.
	
	PROCEDUREDIVISION.	
		
		BOOLEAN MAIN SECTION. BOOLEAN parametroBooleano

			BEGINDECLARATIONS
				nomevar2 PIC BOOLEAN.
			ENDDECLARATIONS
		
			MOVE parametroBooleano TO nomevar2.
			
			IF parametroBooleano THEN
				RETURN nomevar2.
				ENDCOM.
			ELSE
				RETURN parametroBooleano.
				ENDCOM.	
			ENDIF.
			
			#Adicione o hashtag nos retornos e o teste falhará.
			#Uma função com tipo de retorno deve garantir que possui 1 ou mais retornos deste tipo.
		
			ENDCOM.
			
		ENDPROC.

	EXIT.