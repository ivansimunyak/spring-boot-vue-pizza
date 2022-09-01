<template>
  <section class="orders-table">
    <table class="table">
      <thead>
        <tr>
          <th v-for="(header, index) in headers" :key="index">{{ header }}</th>
          <th>Subtotal</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, index) in items" :key="index">
          <td v-for="(column, indexColumn) in columns" :key="indexColumn">
            {{ item[index] }}
          </td>
          <td>{{ item[columnsX[0]] * item[columnsX[1]] }}</td>
        </tr>
      </tbody>
    </table>
  </section>
</template>
<script>
export default {
  props: ["columns", "items", "headers", "columnsX"],
  data() {
    return {};
  },
};
</script>

<style scoped>
.orders-table {
  border: 1px solid #999;
  border-radius: 1px;
  color: #333;
  background: white;
  overflow: auto;
  height: 250px;
  width: 40%;
  position: relative;
  top: 60%;
  margin-left: auto;
  margin-right: auto;
  border-color: #a80000;
}
table {
  border-collapse: collapse;
  width: 100%;
}
th {
  position: sticky;
  top: 0;
  background: #a80000;
  padding: 10px 5px;
  text-align: center;
  border-bottom: 1px solid #a80000;
  color: white;
  z-index: 3;
}
td {
  padding: 5px 5px;
  text-align: center;
  z-index: 1;
  font-family: -apple-system, system-ui, "Segoe UI", Helvetica, Arial,
    sans-serif, "Apple Color Emoji", "Segoe UI Emoji";
}
</style>
