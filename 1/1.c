#include <stdio.h>
#include <stdlib.h>



int main (int argc, char **argv){
		FILE *fd = fopen("/home/Pacco/adventOfCode2018/december/1/data", "r");
		if(fd == NULL){
			printf("RIP\n");
			return -1;
		}
		
		int freq = 0;
		
		int *freqTab = (int *)malloc(64 * 4096 * sizeof(int));
		int *valTab = (int *)malloc(999 * sizeof(int));
		int val = 0;
		int index = 0;
		printf("first loop\n");
		while(fscanf(fd, "%d", &val) == 1){
			freq += val;
			freqTab[index] = freq;
			valTab[index] = val;
			for(int j=0; j < index; j++){
				if(freq == freqTab[j]){
					printf("%d\n",freq);
					break;
				}
			}
			index++;
		}
		printf("second loop\n");
		while(1){
			for(int i = 0; i < 999; i++){
				freq += valTab[i];
				freqTab[index] = freq;
				for(int j = 0; j < index; j++){
					if(freq == freqTab[j]){
					printf("%d\n",freq);
					goto end;
					}
				}
				index++;
			}
		}
	end:
	free(freqTab);
	free(valTab);
	return 0;
}
