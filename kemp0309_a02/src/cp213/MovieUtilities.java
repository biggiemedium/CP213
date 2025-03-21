package cp213;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utilities for working with Movie objects.
 *
 * @author your name, id, email here
 * @version 2025-01-05
 */
public class MovieUtilities {

    /**
     * Counts the number of movies in each genre given in Movie.GENRES. An empty
     * movies list should produce a count array of: [0,0,0,0,0,0,0,0,0,0,0]
     *
     * @param movies List of movies.
     * @return Number of genres across all Movies. One entry for each genre in the
     *         Movie.GENRES array.
     */
    public static int[] genreCounts(final ArrayList<Movie> movies) {
	int[] moviez = new int[Movie.GENRES.length];
	
	for(Movie m : movies) {
		int genre = m.getGenre();
		if(genre > 0) { // We don't want out of bounds index errors
			if(genre < Movie.GENRES.length) { // We don't want out of bounds index errors
				moviez[genre]++;
			}
		}
	}

	return moviez;
    }

    /**
     * Creates a Movie object by requesting data from a user. Uses the format:
     *
     * <pre>
    Title:
    Year:
    Director:
    Rating:
    Genres:
    0: science fiction
    1: fantasy
    ...
    10: mystery
    
    Enter a genre number:
     * </pre>
     *
     * @param keyboard A keyboard (System.in) Scanner.
     * @return A Movie object.
     */
    public static Movie getMovie(final Scanner keyboard) {
    	System.out.print("Title: ");
        String title = keyboard.nextLine();
        
        System.out.print("Year: ");
        int year = keyboard.nextInt();
        keyboard.nextLine();
        
        System.out.print("Director: ");
        String director = keyboard.nextLine();
        
        System.out.print("Rating: ");
        double rating = keyboard.nextDouble();
        
        System.out.println("Genres: ");
    	System.out.println(Movie.genresMenu());

    	System.out.print("Genre number: ");
    	int genre = keyboard.nextInt();
        //System.out.println(new Movie(title, year, director, rating, genre).toString());
        return new Movie(title, year, director, rating, genre);
    }

    /**
     * Creates a list of Movies whose genre is equal to the genre parameter.
     *
     * @param movies List of movies.
     * @param genre  Genre to compare against.
     * @return List of movies of genre.
     */
    public static ArrayList<Movie> getByGenre(final ArrayList<Movie> movies, final int genre) {

    	ArrayList<Movie> mo = new ArrayList();
    	for(Movie m : movies) {
    		if(m.getGenre() == genre) {
    			mo.add(m);
    		}
    	}

    	return mo;
    }

    /**
     * Creates a list of Movies whose ratings are equal to or higher than rating.
     *
     * @param movies List of movies.
     * @param rating Rating to compare against.
     * @return List of movies of rating or higher.
     */
    public static ArrayList<Movie> getByRating(final ArrayList<Movie> movies, final double rating) {

    	ArrayList<Movie> mo = new ArrayList();
    	for(Movie m : movies) {
    		if(m.getRating() == rating) {
    			mo.add(m);
    		}
    	}

    	return mo;
    }

    /**
     * Creates a list of Movies from a particular year.
     *
     * @param movies List of movies.
     * @param year   Year to compare against.
     * @return List of movies of year.
     */
    public static ArrayList<Movie> getByYear(final ArrayList<Movie> movies, final int year) {
    	ArrayList<Movie> mo = new ArrayList();
	for(Movie m : movies) {
		if(m.getYear() == year) {
			mo.add(m);
		}
	}

	return mo;
    }

    /**
     * Asks a user to select a genre from a list of genres displayed by calling
     * Movie.genresMenu() and returns an integer genre code. The genre must be a
     * valid index to an item in Movie.GENRES.
     *
     * @param keyboard A keyboard (System.in) Scanner.
     * @return An integer genre code.
     */
    public static int readGenre(final Scanner keyboard) {
    	int genre = -1;
    	while (true) { // to lazy to fix this but we don't need a for loop
    		genre = keyboard.nextInt();
    	
    		break;
    	}
    	
    	return genre;
    }

    /**
     * Creates and returns a Movie object from a line of formatted string data.
     *
     * @param line A vertical bar-delimited line of movie data in the format
     *             title|year|director|rating|genre
     * @return The data from line as a Movie object.
     */
    public static Movie readMovie(final String line) {

	String[] content = line.split("\\|"); // regex breaks if we use just |
	// we don't really need this 
	//if(content.length < 4) {
	//	return null;
	//}
	
	

	return new Movie(content[0], // title
			Integer.parseInt(content[1]), // year
			content[2], // director
			Double.parseDouble(content[3]), 
			Integer.parseInt(content[4]) 
			); // Kotlin style
    }

    /**
     * Reads a list of Movies from a file.
     *
     * @param fileIn A Scanner of a Movie data file in the format
     *               title|year|director|rating|genre
     * @return A list of Movie objects.
     */
    public static ArrayList<Movie> readMovies(final Scanner fileIn) {
    	ArrayList<Movie> moviez = new ArrayList();

    	while(fileIn.hasNextLine()) {
            Movie movie = readMovie(fileIn.nextLine());
            if (movie != null) { // just in case
            	moviez.add(movie);
            }
    	}
	return moviez;
    }

    /**
     * Writes the contents of a list of Movie to a PrintStream.
     *
     * @param movies A list of Movie objects.
     * @param ps     Output PrintStream.
     */
    public static void writeMovies(final ArrayList<Movie> movies, PrintStream ps) {

	for(Movie m : movies) {
		m.write(ps);
	}

	return;
    }

}
