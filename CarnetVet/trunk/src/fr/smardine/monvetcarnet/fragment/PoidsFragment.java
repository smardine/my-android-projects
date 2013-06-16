package fr.smardine.monvetcarnet.fragment;

import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.ActionBar.LayoutParams;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.smardine.monvetcarnet.R;
import fr.smardine.monvetcarnet.database.accestable.AccesTableCarnet;
import fr.smardine.monvetcarnet.database.accestable.AccesTablePoids;
import fr.smardine.monvetcarnet.helper.DateHelper;
import fr.smardine.monvetcarnet.helper.StringHelper;
import fr.smardine.monvetcarnet.mdl.MlCarnet;
import fr.smardine.monvetcarnet.mdl.MlPoids;

/**
 * Fragment "Poids"
 * @author sims
 */
public class PoidsFragment extends SuperFragment {

	private MlCarnet carnetParent;
	private View view;
	private AccesTablePoids tablePoid;

	public PoidsFragment() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		// LineGraph - class which draws the graph using achartengine

		view = inflater.inflate(R.layout.activity_poids, container, false);

		return view; // return the chart only
		// return view; // return layout but without the chart

	}

	/**
	 * Evenement lancé une fois l'activity créée
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		AccesTableCarnet tableCarnet = new AccesTableCarnet(context);
		List<MlCarnet> listeDeCarnet = tableCarnet.getListeDesCarnets();
		if (listeDeCarnet != null && listeDeCarnet.size() > 0) {
			carnetParent = listeDeCarnet.get(0);
		}

		tablePoid = new AccesTablePoids(context);
		List<MlPoids> listeDesPoids = tablePoid.getListeDePoidsParIdCarnet(carnetParent);
		final GraphicalView chart = construitLeGraph(listeDesPoids);
		((ViewGroup) view).addView(chart, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

	}

	public void metAjourListePoids(MlCarnet p_carnetParent) {
		if (p_carnetParent != null) {
			this.carnetParent = p_carnetParent;
			List<MlPoids> listeDesPoids = tablePoid.getListeDePoidsParIdCarnet(carnetParent);
			final GraphicalView chart = construitLeGraph(listeDesPoids);
			((ViewGroup) view).addView(chart, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		}

	}

	private GraphicalView construitLeGraph(List<MlPoids> listeDesPoids) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();

		renderer.setAxisTitleTextSize(16);
		renderer.setChartTitleTextSize(20);
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(20);
		renderer.setPointSize(5f);
		renderer.setMargins(new int[] { 30, 30, 30, 30 });
		renderer.setMarginsColor(Color.WHITE);
		renderer.setXLabels(5);
		renderer.setYLabels(10);
		renderer.setChartTitle("Courbe de poid");
		renderer.setXTitle("Date");
		renderer.setYTitle("Poids (Kg)");

		renderer.setAxesColor(context.getResources().getColor(R.color.grey));
		renderer.setLabelsColor(Color.BLACK);
		renderer.setApplyBackgroundColor(true);
		renderer.setGridColor(context.getResources().getColor(R.color.grey_light));
		renderer.setShowGrid(true);
		renderer.setShowLegend(true);
		renderer.setShowLabels(true);
		renderer.setZoomEnabled(true, true);
		renderer.setPanEnabled(false);

		// Les séries de données du graphe
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		// List<MlPoids> listeDesPoids = new ArrayList<MlPoids>();
		// listeDesPoids = new AccesTablePoids(context).getListeDePoidsParIdCarnet(carnetParent);
		//
		// MlPoids unPoid = new MlPoids(carnetParent);
		// unPoid.setDate(new Date(2010 - 1900, 01, 01));
		// unPoid.setValeur(5.5);
		//
		// MlPoids deuxPoid = new MlPoids(carnetParent);
		// deuxPoid.setDate(new Date(2011 - 1900, 02, 03));
		// deuxPoid.setValeur(7.5);
		// deuxPoid.setNote("Totot");
		//
		// MlPoids troisPoid = new MlPoids(carnetParent);
		// troisPoid.setDate(new Date(2013 - 1900, 04, 20));
		// troisPoid.setValeur(6.5);
		//
		// MlPoids quatrePoid = new MlPoids(carnetParent);
		// quatrePoid.setDate(new Date(2012 - 1900, 04, 20));
		// quatrePoid.setValeur(1.5);
		//
		// listeDesPoids.add(unPoid);
		// listeDesPoids.add(deuxPoid);
		// listeDesPoids.add(troisPoid);
		// listeDesPoids.add(quatrePoid);

		XYSeriesRenderer r = new XYSeriesRenderer();
		r.setColor(Color.RED);
		r.setPointStyle(PointStyle.DIAMOND);
		r.setDisplayChartValues(true);
		r.setLineWidth(3);
		r.setChartValuesTextSize(15);
		r.setGradientEnabled(true);
		renderer.addSeriesRenderer(r);
		TimeSeries series = new TimeSeries(carnetParent.getNomCarnet());
		renderer.setXLabels(0);
		for (MlPoids unPoids : listeDesPoids) {
			// On indique le style de la serie
			series.add(unPoids.getDate(), unPoids.getValeur());
			renderer.addXTextLabel(unPoids.getDate().getTime(), DateHelper.MMMYYYY(unPoids.getDate()));
			if (!StringHelper.IsNullOrEmpty(unPoids.getNote())) {
				series.addAnnotation(unPoids.getNote(), unPoids.getDate().getTime(), unPoids.getValeur());
			}
		}
		dataset.addSeries(series);
		renderer.setYAxisMax(series.getMaxY() + 5);
		renderer.setYAxisMin(0);
		renderer.setXAxisMax(series.getMaxX() + 200);
		renderer.setXAxisMin(series.getMinX() - 1000);

		// renderer.setPanLimits(new double[] { renderer.getXAxisMin() - 10, renderer.getXAxisMax() + 10, renderer.getYAxisMin() - 10,
		// renderer.getYAxisMax() + 10 });

		return ChartFactory.getTimeChartView(context, dataset, renderer, "MMM/YYYY");//
		// LineChartView(context, dataset, renderer);//
		// getLineChartIntent(context, dataset, renderer);

	}
}
