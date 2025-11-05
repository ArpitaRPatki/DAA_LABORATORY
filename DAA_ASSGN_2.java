//Arpita Patki 123B1F001
//Assignment 2  Implement Quicksort to efficiently sort movies based on various user-selected parameters.

import java.util.*;


class Movie {
    String title;
    double imdbRating;
    int releaseYear;
    int watchTimePopularity; // in millions (simulated popularity metric)

    public Movie(String title, double imdbRating, int releaseYear, int watchTimePopularity) {
        this.title = title;
        this.imdbRating = imdbRating;
        this.releaseYear = releaseYear;
        this.watchTimePopularity = watchTimePopularity;
    }

    @Override
    public String toString() {
        return String.format("%-30s | IMDb: %.1f | Year: %d | WatchTime: %dM",
                title, imdbRating, releaseYear, watchTimePopularity);
    }
}

public class DAA_ASSGN_2 {

    
    public static void quickSort(Movie[] movies, int low, int high, String criteria) {
        if (low < high) {
            int pi = partition(movies, low, high, criteria);
            quickSort(movies, low, pi - 1, criteria);
            quickSort(movies, pi + 1, high, criteria);
        }
    }

    private static int partition(Movie[] movies, int low, int high, String criteria) {
        Movie pivot = movies[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            boolean condition = false;

            switch (criteria.toLowerCase()) {
                case "imdb":
                    condition = movies[j].imdbRating >= pivot.imdbRating;
                    break;
                case "year":
                    condition = movies[j].releaseYear >= pivot.releaseYear;
                    break;
                case "popularity":
                    condition = movies[j].watchTimePopularity >= pivot.watchTimePopularity;
                    break;
            }

            if (condition) {
                i++;
                Movie temp = movies[i];
                movies[i] = movies[j];
                movies[j] = temp;
            }
        }

        Movie temp = movies[i + 1];
        movies[i + 1] = movies[high];
        movies[high] = temp;

        return i + 1;
    }

    
    public static void displayMovies(Movie[] movies) {
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

       
        Movie[] movies = {
                new Movie("3 Idiots", 8.4, 2009, 95),
                new Movie("Dangal", 8.3, 2016, 120),
                new Movie("Taare Zameen Par", 8.4, 2007, 85),
                new Movie("PK", 8.1, 2014, 110),
                new Movie("Zindagi Na Milegi Dobara", 8.2, 2011, 90),
                new Movie("Lagaan", 8.1, 2001, 70),
                new Movie("Swades", 8.2, 2004, 65),
                new Movie("Gully Boy", 7.9, 2019, 100),
                new Movie("Queen", 8.1, 2013, 75),
                new Movie("Barfi!", 8.1, 2012, 80),
                new Movie("Andhadhun", 8.2, 2018, 88),
                new Movie("Chhichhore", 8.2, 2019, 95),
                new Movie("Drishyam", 8.3, 2015, 92),
                new Movie("Kantara", 8.3, 2022, 130),
                new Movie("Gangs of Wasseypur", 8.2, 2012, 70),
                new Movie("Article 15", 8.0, 2019, 60),
                new Movie("Raazi", 7.8, 2018, 72),
                new Movie("Mimi", 8.1, 2021, 68),
                new Movie("Pathaan", 6.8, 2023, 140),
                new Movie("Jawan", 7.6, 2023, 135),
                new Movie("RRR", 8.0, 2022, 150),
                new Movie("Brahmastra", 6.6, 2022, 125),
                new Movie("Stree", 7.5, 2018, 78),
                new Movie("Bhool Bhulaiyaa 2", 6.7, 2022, 85),
                new Movie("Kabir Singh", 7.1, 2019, 115)
        };

        System.out.println(" Welcome to StreamFlix Movie Recommendation System ");
        System.out.println("-------------------------------------------------------");
        System.out.println("You can sort movies based on:");
        System.out.println("1. IMDb Rating");
        System.out.println("2. Release Year");
        System.out.println("3. Watch Time Popularity");
        System.out.print("\nEnter your choice (1/2/3): ");
        int choice = sc.nextInt();

        String criteria = "";
        switch (choice) {
            case 1:
                criteria = "imdb";
                break;
            case 2:
                criteria = "year";
                break;
            case 3:
                criteria = "popularity";
                break;
            default:
                System.out.println("Invalid choice! Sorting by IMDb rating by default.");
                criteria = "imdb";
        }

        System.out.println("\nBefore Sorting:\n");
        displayMovies(movies);

        long startTime = System.nanoTime();
        quickSort(movies, 0, movies.length - 1, criteria);
        long endTime = System.nanoTime();

        System.out.println("\nAfter Sorting by " + criteria.toUpperCase() + ":\n");
        displayMovies(movies);

        System.out.println("\nSorting Time: " + (endTime - startTime) / 1e6 + " ms");

        System.out.println("\n Sorting completed efficiently using QuickSort!");
        sc.close();
    }
}

