IDENTIFICATORDIVISION. PROGRAMID. testeRetorno.

#esse teste verifica que o retorno de MAIN é válido para o retorno de funcaoTeste
	
	PROCEDUREDIVISION.	
		
		INTEGER MAIN SECTION. INTEGER parametroInteiro

			BEGINDECLARATIONS
				varLocal1 PIC INTEGER.
			ENDDECLARATIONS
		
			MOVE parametroInteiro TO varLocal1.
			
			RETURN varLocal1.
		
			ENDCOM.
			
		ENDPROC.
		
		INTEGER funcaoTeste SECTION. INTEGER parametro1
			BEGINDECLARATIONS
			ENDDECLARATIONS
			
			RETURN (PERFORM MAIN USING parametro1).
			
			ENDCOM.
			
		ENDPROC.

	EXIT.