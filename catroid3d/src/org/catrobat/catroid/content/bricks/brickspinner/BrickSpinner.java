/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2018 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.catrobat.catroid.content.bricks.brickspinner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.catrobat.catroid.common.Nameable;

import java.util.List;

public class BrickSpinner<T extends Nameable> implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private BrickSpinnerAdapter adapter;

    private OnItemSelectedListener<T> onItemSelectedListener;

    public BrickSpinner(int spinnerId, @NonNull View parent, List<Nameable> items) {
        adapter = new BrickSpinnerAdapter(parent.getContext(), android.R.layout.simple_spinner_item, items);
        spinner = parent.findViewById(spinnerId);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(this);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener<T> onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (onItemSelectedListener == null) {
            return;
        }

        Nameable item = adapter.getItem(position);

        if (item.getClass().equals(NewOption.class)) {
            return;
        }

        if (item.getClass().equals(StringOption.class)) {
            onItemSelectedListener.onStringOptionSelected(item.getName());
            return;
        }

        onItemSelectedListener.onItemSelected((T) item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void add(@NonNull T item) {
        adapter.add(item);
    }

    public List<T> getItems() {
        return (List<T>) adapter.getItems();
    }

    public void setSelection(int position) {
        spinner.setSelection(position);
    }

    public void setSelection(@Nullable String itemName) {
        spinner.setOnItemSelectedListener(null);

        int position = adapter.getPosition(itemName);
        position = consolidateSpinnerSelection(position);

        spinner.setSelection(position);
        onSelectionSet(adapter.getItem(position));
        spinner.setOnItemSelectedListener(this);
    }

    public void setSelection(@Nullable T item) {
        spinner.setOnItemSelectedListener(null);

        int position = adapter.getPosition(item);
        position = consolidateSpinnerSelection(position);

        spinner.setSelection(position);
        onSelectionSet(adapter.getItem(position));
        spinner.setOnItemSelectedListener(this);
    }

    private int consolidateSpinnerSelection(int position) {
        if (position == -1) {
            if (adapter.containsNewOption()) {
                position = adapter.getCount() > 1 ? 1 : 0;
            } else {
                position = 0;
            }
        }
        return position;
    }

    private void onSelectionSet(Nameable selectedItem) {
        if (onItemSelectedListener != null) {
            if (selectedItem.getClass().equals(NewOption.class)) {
                onItemSelectedListener.onItemSelected(null);
                return;
            }
            if (selectedItem.getClass().equals(StringOption.class)) {
                onItemSelectedListener.onStringOptionSelected(selectedItem.getName());
                return;
            }
            onItemSelectedListener.onItemSelected((T) selectedItem);
        }
    }

    public interface OnItemSelectedListener<T> {

        void onNewOptionSelected();

        void onStringOptionSelected(String string);

        void onItemSelected(@Nullable T item);
    }

    private class BrickSpinnerAdapter extends ArrayAdapter<Nameable> {

        private List<Nameable> items;

        BrickSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Nameable> items) {
            super(context, resource, items);
            this.items = items;
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext())
                        .inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
            }

            final Nameable item = getItem(position);
            ((TextView) convertView).setText(item.getName());
            convertView.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getActionIndex() == MotionEvent.ACTION_DOWN && item.getClass().equals(NewOption.class)) {
                        onItemSelectedListener.onNewOptionSelected();
                    }
                    return false;
                }
            });
            return convertView;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext())
                        .inflate(android.R.layout.simple_spinner_item, parent, false);
            }

            Nameable item = getItem(position);
            ((TextView) convertView).setText(item.getName());
            return convertView;
        }

        int getPosition(@Nullable String itemName) {
            for (int position = 0; position < getCount(); position++) {
                if (getItem(position).getName().equals(itemName)) {
                    return position;
                }
            }

            return -1;
        }

        List<Nameable> getItems() {
            return items;
        }

        boolean containsNewOption() {
            for (Nameable item : items) {
                if (item instanceof NewOption) {
                    return true;
                }
            }
            return false;
        }
    }
}
