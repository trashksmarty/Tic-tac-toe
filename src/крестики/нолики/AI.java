package крестики.нолики;

public class AI {

    public boolean checkWinX(int[] game) {
        if (game[0] == 2 && game[1] == 2 && game[2] == 2
         || game[3] == 2 && game[4] == 2 && game[5] == 2
         || game[6] == 2 && game[7] == 2 && game[8] == 2

         || game[0] == 2 && game[3] == 2 && game[6] == 2
         || game[1] == 2 && game[4] == 2 && game[7] == 2
         || game[2] == 2 && game[5] == 2 && game[8] == 2

         || game[0] == 2 && game[4] == 2 && game[8] == 2
         || game[2] == 2 && game[4] == 2 && game[6] == 2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkWinO(int[] game) {
        if (game[0] == 1 && game[1] == 1 && game[2] == 1
         || game[3] == 1 && game[4] == 1 && game[5] == 1
         || game[6] == 1 && game[7] == 1 && game[8] == 1

         || game[0] == 1 && game[3] == 1 && game[6] == 1
         || game[1] == 1 && game[4] == 1 && game[7] == 1
         || game[2] == 1 && game[5] == 1 && game[8] == 1

         || game[0] == 1 && game[4] == 1 && game[8] == 1
         || game[2] == 1 && game[4] == 1 && game[6] == 1) {
            return true;
        } else {
            return false;
        }
    }

    public int nextStepEasy(int[] game) {
        int cell =(int) (Math.random() * 9);
        boolean notFree = true;
        while(notFree){
            if(game[cell] != 0){
                cell =(int) (Math.random() * 9);
            } else {
                notFree = false;
            }
        }
        return cell;
    }

//    public int nextStepHard(int[] game) {
//        if(game[4] != 0) return 4;
//        else if ()
//    }
}
