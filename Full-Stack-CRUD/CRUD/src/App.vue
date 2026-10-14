  
<template>
  <div>
    <h1>Student Management System</h1>

    <form @submit.prevent="saveStudent">
      <input v-model="form.name" placeholder="Name" required>
      <input v-model="form.department" placeholder="Department" required>
      <input v-model="form.email" type="email" placeholder="Email" required>

      <button type="submit">
        {{ editingId !== null ? "Update" : "Add" }}
      </button>
      <button type="button" @click="resetForm">Clear</button>
    </form>

    <h2>Search Student</h2>
    <input v-model="search" placeholder="Enter student name">
    <button type="button" @click="searchStudent">Search</button>
    <button type="button" @click="loadStudents">Show All</button>

    <h2>Student List</h2>

    <div v-for="s in students" :key="s.id">
      {{ s.name }} - {{ s.department }} - {{ s.email }}
      <button @click="editStudent(s)">Edit</button>
      <button @click="deleteStudent(s.id)">Delete</button>
      <hr>
    </div>

    <p>{{ message }}</p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      students: [],
      form: { name: "", department: "", email: "" },
      editingId: null,
      search: "",
      message: ""
    };
  },

  methods: {
    async loadStudents() {
      let res = await fetch("http://localhost:8080/students");
      this.students = await res.json();
    },

    async searchStudent() {
      let res = await fetch(
        "http://localhost:8080/students/search?name=" +
        encodeURIComponent(this.search)
      );

      if (res.ok) {
        let student = await res.json();
        this.students = student ? [student] : [];
      } else {
        this.students = [];
      }
    },

    async saveStudent() {
      let url = "http://localhost:8080/students";
      let method = "POST";

      if (this.editingId !== null) {
        url += "/" + this.editingId;
        method = "PUT";
      }

      await fetch(url, {
        method: method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(this.form)
      });

      this.message = "Saved successfully";
      this.resetForm();
      await this.loadStudents();
    },

    editStudent(s) {
      this.form = {
        name: s.name,
        department: s.department,
        email: s.email
      };
      this.editingId = s.id;
    },

    async deleteStudent(id) {
      await fetch("http://localhost:8080/students/" + id, {
        method: "DELETE"
      });

      this.message = "Deleted successfully";
      await this.loadStudents();
    },

    resetForm() {
      this.form = { name: "", department: "", email: "" };
      this.editingId = null;
    }
  },

  mounted() {
    this.loadStudents();
  }
};
</script>

<style>
body {
  font-family: Arial, sans-serif;
  background: #f0f4f8;
  margin: 30px;
}

input, button {
  padding: 10px;
  margin: 5px;
}

button {
  background: blue;
  color: white;
  border: none;
  cursor: pointer;
}

hr {
  border: 1px solid #ddd;
}
</style>

