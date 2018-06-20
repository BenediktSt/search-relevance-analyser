        List<String> searchTerms = relevanceEntries
                .stream()
                .map(TrackingEntry::getSearchTerm)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(searchTerms);

        List<InterDataPoint> intermediate = new ArrayList<>();
        searchTerms.forEach(searchTerm -> {
            relevanceEntries
                    .stream()
                    .filter(e -> e.getSearchTerm().equals(searchTerm))
                    .map(TrackingEntry::getRank)
                    .distinct()
                    .sorted()
                    .forEach(rank -> {
                        intermediate.add(new InterDataPoint(searchTerm, rank));
                    });
        });
        System.out.println(intermediate);

        List<DataPoint> result = new ArrayList<>();
        intermediate.forEach(interDataPoint -> {
            result.add(new DataPoint(
                    interDataPoint.searchTearm,
                    interDataPoint.rank,
                    (int) relevanceEntries
                            .stream()
                            .filter(e -> e.getSearchTerm().equals(interDataPoint.searchTearm) && e.getRank() == interDataPoint.rank)
                            .count()
            ));
        });

        System.out.println(result);