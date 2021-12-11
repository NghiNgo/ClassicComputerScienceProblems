package chapter3;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import chapter3.WordGrid.GridLocation;

public class WordSearchConstraint extends Constraint<String, List<GridLocation>> {

	public WordSearchConstraint(List<String> words) {
		super(words);
	}

	@Override
	public boolean satisfied(Map<String, List<GridLocation>> assignment) {
		// combine all GridLocations into one giant List
		List<GridLocation> allLocations = assignment.values().stream()
				.flatMap(Collection::stream).collect(Collectors.toList());
		// a set will eliminate duplicates using equals()
		Set<GridLocation> allLocationsSet = new HashSet<>(allLocations);
		// if there are any duplicate grid locations then there is an overlap
		return allLocations.size() == allLocationsSet.size();
	}

	public static void main(String[] args) {
		WordGrid grid = new WordGrid(9, 9);
		List<String> words = List.of("MATTHEW", "JOE", "MARY", "SARAH", "SALLY");
		// generate domains for all words
		Map<String, List<List<GridLocation>>> domains = new HashMap<>();
		for (String word : words) {
			domains.put(word, grid.generateDomain(word));
		}
		CSP<String, List<GridLocation>> csp = new CSP<>(words, domains);
		csp.addConstraint(new WordSearchConstraint(words));
		Map<String, List<GridLocation>> solution = csp.backtrackingSearch();
		if (solution == null) {
			System.out.println("No solution found!");
		} else {
			Random random = new Random();
			for (Entry<String, List<GridLocation>> item : solution.entrySet()) {
				String word = item.getKey();
				List<GridLocation> locations = item.getValue();
				// random reverse half the time
				if (random.nextBoolean()) {
					Collections.reverse(locations);
				}
				grid.mark(word, locations);
			}
			System.out.println(grid);
		}
	}

}
