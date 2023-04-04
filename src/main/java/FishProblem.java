/**
 * This task is about having different fish species, which like to eat each other. The different
 * species are never eating fishes of the same species and they are only able to eat specific
 * other species.
 * The following list describes the fish species known (together with it's abbreviation used in the
 * input String) and the other species, that this species is eating.
 * * Shark (A) eats Salmon (B) and Herring (C)
 * * Salmon (B) eats Herring (C) and Sardine (D)
 * * Herring (C) eats Sardine (D)
 * * Sardine (D) is too small and eats no other fish
 * Files
 * ta!
 * Write a function:
 * class Solution ( public int solution(String S);
 * that, given a string S describing a row of fishes, returns an integer that gives the number of
 * fishes surviving, after all fishes have eaten their neighbors, that they are able to eat. The
 * function has to be applied, until there are no neighbours available any longer, that could be
 * eaten. Also the function should find the smallest number of surviving fishes, if there are
 * several possible sequences of eating the neighbours.
 * Assume that:
 * * Length of String S is in range of [0…1.000.000]
 * * Every character in String S is out of the list: 'A, 'B', 'C' and 'D'
 * For example:
 * Given a String "ABAD" the result will be 3, because the sharks will eat the Salmon and the rest
 * will survive.
 * Given a String "ACCD" the result will be 1, because the Herring will eat the Sardine and the
 * Shark will finally eat the Herrings.
 */
public class FishProblem {
    public static int solution(String s) {
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(solution("ABAD"));
    }
}
