package leetdaily.medium;

import java.util.HashSet;
import java.util.Set;

public class WalkingRobot874 {
//    slightly larger than 2 * max coordinate value (next prime to avoid hash collision as far as possible)
    private static final int HASH_MULTIPLIER = 60013;
    public static void main(String[] args) {
        WalkingRobot874 w = new WalkingRobot874();
        int[] commands = {4,-1,4,-2,4};
        int[][] obstacles = {{2,4}};
        System.out.println(w.robotSim(commands, obstacles));
    }

//    simulation; time: O(m+n), space: O(n) [m - commands, n - obstacles]
    public int robotSim(int[] commands, int[][] obstacles) {
//        store obstacles in a hashset for efficient lookup
        Set<Integer> obstacleSet = new HashSet<>();
        for(int[] obstacle : obstacles)
            obstacleSet.add(hashCoordinates(obstacle[0], obstacle[1]));
//        define direction vectors : North, East, South, West
        int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
        int[] currentPosition = {0,0};
        int maxDistanceSquared = 0;
        int currentDirection = 0; // 0: North, 1: East, 2: South, 3: West
        for(int command : commands) {
            if(command == -1) {
//                turn right
                currentDirection = (currentDirection + 1) % 4;
                continue;
            }
            if(command == -2) {
//                turn left
                currentDirection = (currentDirection + 3) % 4;
                continue;
            }
//            move forward
            int[] direction = directions[currentDirection];
            for(int step = 0 ; step < command ; step++) {
                int nextX = currentPosition[0] + direction[0];
                int nextY = currentPosition[1] + direction[1];
                if(obstacleSet.contains(hashCoordinates(nextX, nextY)))
                    break;
                currentPosition[0] = nextX;
                currentPosition[1] = nextY;
            }

            maxDistanceSquared = Math.max(maxDistanceSquared, currentPosition[0] * currentPosition[0] +
                    currentPosition[1] * currentPosition[1]);

        }

        return maxDistanceSquared;

    }

//    hash function to convert (x,y) coordinates to a unique integer value
    private static int hashCoordinates(int x, int y) {
        return x + HASH_MULTIPLIER * y;
    }
}

/*
A robot on an infinite XY-plane starts at point (0, 0) facing north. The robot can receive a sequence of these three possible types of commands:
-2: Turn left 90 degrees.
-1: Turn right 90 degrees.
1 <= k <= 9: Move forward k units, one unit at a time.
Some grid squares are obstacles. The ith obstacle is at grid point obstacles[i] = (xi, yi). If the robot runs into an obstacle, then it will instead stay in its current location and move on to the next command.
Return the maximum Euclidean distance that the robot ever gets from the origin squared (i.e. if the distance is 5, return 25).
Note:
North means +Y direction.
East means +X direction.
South means -Y direction.
West means -X direction.
There can be obstacle in [0,0].

Example 1:
Input: commands = [4,-1,3], obstacles = []
Output: 25
Explanation: The robot starts at (0, 0):
1. Move north 4 units to (0, 4).
2. Turn right.
3. Move east 3 units to (3, 4).
The furthest point the robot ever gets from the origin is (3, 4), which squared is 32 + 42 = 25 units away.
Example 2:
Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
Output: 65
Explanation: The robot starts at (0, 0):
1. Move north 4 units to (0, 4).
2. Turn right.
3. Move east 1 unit and get blocked by the obstacle at (2, 4), robot is at (1, 4).
4. Turn left.
5. Move north 4 units to (1, 8).
The furthest point the robot ever gets from the origin is (1, 8), which squared is 12 + 82 = 65 units away.
Example 3:
Input: commands = [6,-1,-1,6], obstacles = []
Output: 36
Explanation: The robot starts at (0, 0):
1. Move north 6 units to (0, 6).
2. Turn right.
3. Turn right.
4. Move south 6 units to (0, 0).
The furthest point the robot ever gets from the origin is (0, 6), which squared is 62 = 36 units away.

Constraints:
1 <= commands.length <= 104
commands[i] is either -2, -1, or an integer in the range [1, 9].
0 <= obstacles.length <= 104
-3 * 104 <= xi, yi <= 3 * 104
The answer is guaranteed to be less than 231.
 */