# GenericRecyclerViewAdapter
Are you tired of creating separate adapter classes for each RecyclerView? Then use this generic adapter.


 Use it:
 
GenericRecyclerViewAdapter genericRecyclerViewAdapter = new GenericRecyclerViewAdapter<>("A", getActivity(),                  PreferenceManager.getInstance().getHomeResponse().getProduct(), this);
rvAddress.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));
 rvAddress.setAdapter(genericRecyclerViewAdapter);
