import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Test3 {
    public static void main(String[] args) {

    }

    int[][] findOneRectangle(int[][] board) {
        int[][] result = new int[2][2];
        if ( board.length == 0 || board[0].length == 0) {
            return result;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    result[0][0] = i;
                    result[0][1] = j;
                    int height = 1, width = 1;
                    while (i + height < board.length && board[i + height][j] == 0) {
                        height++;
                    }
                    while (j + width < board[0].length && board[i][j + width] == 0) {
                        width++;
                    }
                    result[1][0] = i + height - 1;
                    result[1][1] = j + width - 1;

                    break;
                }
                if (result.length != 0) {
                    break;
                }
            }
        }
        return result;
    }

    int[][] invalidBadgeRecords(String[][] records) {
        int[][] result = new int[1][2];
        if (records.length == 0) {
            return result;
        }

        // 0 for exited, 1 for entered
  Map<String, Integer> state = new HashMap<>();
  Set<String> invalidEnter = new HashSet<>();
  Set<String> invalidExit = new HashSet<>();
        for (String[] record : records) {
            String name = record[0];
            String action = record[1];
            if(!state.containsKey(name)){
                state.put(name, 0);
            }
            if (action.equals("enter") ) {
                if (state.get(name) == 0) {
                    state.put(name, 1);
                } else {
                    invalidEnter.add(name);
                }
            } else {
                if (state.get(name) == 1) {
                    state.put(name, 0);
                } else {
                    invalidExit.add(name);
                }
            }
        }
        for (Map.Entry<String, Integer> entry: state.entrySet()) {
            if (entry.getValue() == 1) {
                invalidEnter.add(entry.getKey());
            }
        }
        // result .add invalidEnter, result.add invalidExit
        return result;
    }
}
