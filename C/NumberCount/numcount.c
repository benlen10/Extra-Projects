# include<stdio.h>

main() {
	int num[10];
	int white,ch,c,i,j;
	white = ch = 0;
	for ( i = 0; i < 10; i++){
		num[i]=0;
	}
	while((c = getchar()) != EOF) {
		if (c >='0' && c <='9'){
			num[c-'0']++;
		}
		if (c == ' '){
			white++;
		}
		else {
			ch++;
		}
		}
		printf("Number Count ");
		i = 0;
		for (i=0; i<10; i++){
			printf("Num:%d  ",(i+1));
			for (j=0; j< num[i]; j++){
			printf("- ");
			}
			printf("\n");
			
	}
	printf("ch Count: %d\n", ch);
	printf("White Count: %d\n", white);
}
	
