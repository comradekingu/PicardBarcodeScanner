/*
 * Copyright (C) 2012 Philipp Wolfer <ph.wolfer@googlemail.com>
 * 
 * This file is part of MusicBrainz Picard Barcode Scanner.
 * 
 * MusicBrainz Picard Barcode Scanner is free software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * 
 * MusicBrainz Picard Barcode Scanner is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * MusicBrainz Picard Barcode Scanner. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.musicbrainz.picard.barcodescanner.tasks;

import android.os.AsyncTask;

public abstract class AsyncCallbackTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
	
	private Boolean mError = false;
	
	private TaskCallback<Result> mCallback;
	
	public TaskCallback<Result> getCallback() {
		return mCallback;
	}

	public void setCallback(TaskCallback<Result> callback) {
		this.mCallback = callback;
	}

	private TaskCallback<Exception> mErrorCallback;
	
	public TaskCallback<Exception> getErrorCallback() {
		return mErrorCallback;
	}

	public void setErrorCallback(TaskCallback<Exception> callback) {
		this.mErrorCallback = callback;
	}

	@Override
	protected void onPostExecute(Result result) {
		if (mCallback != null && !mError) {
			mCallback.onResult(result);
		}
	}
	
	/*
	 * Called when an error occurred.
	 * 
	 * This method should be called by the implementation whenever an error
	 * is preventing the successful termination of the task. Calling onError()
	 * will prevent the normal callback being called.
	 */
	protected void onError(Exception ex) {
		if (mErrorCallback != null) {
			mError = true;
			mErrorCallback.onResult(ex);
		}
	}
}
